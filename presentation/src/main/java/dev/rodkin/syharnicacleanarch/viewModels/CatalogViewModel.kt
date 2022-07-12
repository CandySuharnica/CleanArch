package dev.rodkin.syharnicacleanarch.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rodkin.domain.entities.BasketItem
import dev.rodkin.domain.entities.CatalogItem
import dev.rodkin.domain.useCases.*
import dev.rodkin.domain.utils.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val catalogListUseCase: GetCatalogItemsListUseCase,
    private val basketFlow: GetBasketItemFlowUseCase,
    private val getTypeUseCase: GetCatalogItemTypeUseCase,
    private val sortCatalogListUseCase: SortCatalogItemTypeUseCase,
    private val updateBasketUseCase: UpdateBasketItemsUseCase,
    private val all: InitialTypes.ALL,
) : ViewModel() {

    private val _catalogList = MutableStateFlow(emptyList<CatalogItem>())
    val catalogList: StateFlow<List<CatalogItem>> = _catalogList

    private val _basketList = MutableStateFlow(emptyList<BasketItem>())
    val basketList: StateFlow<List<BasketItem>> = _basketList

    private var unsortedList: List<CatalogItem> = emptyList()

    private val _catalogTypes = MutableStateFlow(emptyList<String>())
    val catalogTypes: StateFlow<List<String>> = _catalogTypes

    private val _exception = MutableStateFlow("")
    val exception: StateFlow<String> = _exception

    val flowSearch = MutableStateFlow("")
    val flowType = MutableStateFlow(all.type)
    val flowSearchMode = MutableStateFlow(SortMode.NONE)

    init {
        viewModelScope.apply {
            launch {
                getCatalogList()
            }
            launch {
                flowType.collect {
                    _catalogList.tryEmit(sortCatalogList())
                }
            }
            launch {
                flowSearch.collect {
                    _catalogList.tryEmit(sortCatalogList())
                }
            }
            launch {
                flowSearchMode.collect {
                    _catalogList.tryEmit(sortCatalogList())
                }
            }
            launch {
                basketFlow.getBasketFlow().collect { basketList ->
                    _basketList.value = basketList
                }
            }
        }
    }

    fun updateBasket(item: CatalogItem, onBasketMode: OnBasketMode) {
        viewModelScope.launch {
            updateBasketUseCase.updateBasketItems(item, onBasketMode)
        }
    }

    fun sortCatalogList(): List<CatalogItem> =
        sortCatalogListUseCase.sortCatalogList(
            unsortedList = unsortedList,
            searchSort = flowSearch.value,
            typeSort = flowType.value,
            sortMode = flowSearchMode.value
        )

    fun getItemFromId(id: Long): CatalogItem? {
        try {
            return catalogList.value.find { it.id == id }
                ?: throw Exception("Упс такого элемента не существует")
        } catch (e: Exception) {
            _exception.value = e.message ?: " "
        }
        return null
    }

    suspend fun getCatalogList() {
        val response = catalogListUseCase.getCatalogListResponse()
        when (response) {
            is Response.Success -> {
                val catalogList = response.data
                val types = getTypeUseCase.getCatalogTypes(catalogList)
                _catalogTypes.value = types
                _catalogList.value = catalogList
                unsortedList = catalogList
            }
            is Response.Error -> {
                val error = response.exception
                _exception.value = error
            }
        }
    }

}

