package dev.rodkin.syharnicacleanarch.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rodkin.domain.entities.CatalogItem
import dev.rodkin.domain.useCases.GetCatalogItemTypeUseCase
import dev.rodkin.domain.useCases.GetCatalogItemsListUseCase
import dev.rodkin.domain.useCases.SortCatalogItemTypeUseCase
import dev.rodkin.domain.useCases.SortMode
import dev.rodkin.domain.utils.Response
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val catalogListUseCase: GetCatalogItemsListUseCase,
    private val getTypeUseCase: GetCatalogItemTypeUseCase,
    private val sortCatalogListUseCase: SortCatalogItemTypeUseCase
) : ViewModel() {

    private val _catalogList = MutableStateFlow(emptyList<CatalogItem>())
    val catalogList: StateFlow<List<CatalogItem>> = _catalogList

    private val _catalogTypes = MutableStateFlow(emptyList<String>())
    val catalogTypes: StateFlow<List<String>> = _catalogTypes

    private val _exception = MutableStateFlow("")
    val exception: StateFlow<String> = _exception

    val flowSearch = MutableStateFlow("")
    val flowType = MutableStateFlow<String?>(null)
    val flowSearchMode = MutableStateFlow(SortMode.NONE)

    init {
        getCatalogList()
    }

    fun sortCatalogList() {
        sortCatalogListUseCase.sortCatalogList(
            unsortedList = catalogList.value,
            searchSort = flowSearch.value,
            typeSort = flowType.value ?: "",
            sortMode = flowSearchMode.value
        )
    }

    fun getItemFromId(id: Long): CatalogItem? {
        try {
            return catalogList.value.find { it.id == id }
                ?: throw Exception("Упс такого элемента не существует")
        } catch (e: Exception) {
            _exception.value = e.message ?: " "
        }
        return null
    }

    fun getCatalogList() {
        viewModelScope.launch {
            val response = catalogListUseCase.getCatalogListResponse()
            when (response) {
                is Response.Success -> {
                    val catalogList = response.data
                    val types = getTypeUseCase.getCatalogTypes(catalogList)
                    _catalogTypes.value = types
                    _catalogList.value = catalogList
                }
                is Response.Error -> {
                    val error = response.exception
                    _exception.value = error
                }
            }
        }
    }

}

