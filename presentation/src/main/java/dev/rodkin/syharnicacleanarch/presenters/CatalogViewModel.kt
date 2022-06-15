package dev.rodkin.syharnicacleanarch.presenters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rodkin.domain.entities.CatalogItem
import dev.rodkin.domain.useCases.CatalogUseCases
import dev.rodkin.domain.useCases.useCasesImpl.InitialTypes
import dev.rodkin.domain.useCases.useCasesImpl.SortMode
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.reflect.jvm.internal.impl.load.kotlin.JvmType

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val useCase: CatalogUseCases
) : ViewModel() {

    private val _catalogList = MutableStateFlow(emptyList<CatalogItem>())
    val catalogList: StateFlow<List<CatalogItem>> = _catalogList

    private val _catalogTypes = MutableStateFlow(emptyList<String>())
    val catalogTypes: StateFlow<List<String>> = _catalogTypes

    private val _exception = MutableStateFlow("")
    val exception: StateFlow<String> = _exception

    val flowSearch = MutableStateFlow("")
    val flowType = MutableStateFlow(InitialTypes.ALL.type)
    val flowSearchMode = MutableStateFlow(SortMode.NONE)

    init {
        viewModelScope.apply {
            launch {
                //init catalog list
                useCase.getCatalogList()
                sortCatalogList()
            }
            launch {
                useCase.catalogTypes.collect { catalogTypes ->
                    _catalogTypes.emit(catalogTypes)
                }
            }
            launch {
                useCase.catalogList.collect { catalogList ->
                    _catalogList.tryEmit(catalogList)
                }
            }
        }
    }

    fun sortCatalogList() {
        viewModelScope.launch {
            useCase.sortCatalogList(
                searchSort = flowSearch.value,
                typeSort = flowType.value,
                sortMode = flowSearchMode.value,
            )
        }
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

}