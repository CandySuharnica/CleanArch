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

@HiltViewModel
class CatalogViewModel @Inject constructor(
   private val useCase: CatalogUseCases
) : ViewModel() {

    private val _catalogList = MutableStateFlow(emptyList<CatalogItem>())
    val catalogList: StateFlow<List<CatalogItem>> = _catalogList

    private val _catalogTypes = MutableStateFlow(emptyList<String>())
    val catalogTypes: StateFlow<List<String>> = _catalogTypes

    val flowSearch = MutableStateFlow("")
    val flowType = MutableStateFlow(InitialTypes.ALL.type)
    val flowSearchMode = MutableStateFlow(SortMode.NONE)

    fun getCatalogList() {
        viewModelScope.launch {
            //init catalog list
            useCase.getCatalogList()
            sortCatalogList()

            val catalogTypes = useCase.catalogTypes.value
            _catalogTypes.emit(catalogTypes)
            useCase.catalogList.collect{
                _catalogList.emit(it)
            }
        }
    }

    fun sortCatalogList(){
        viewModelScope.launch {
            useCase.sortCatalogList(
                searchSort = flowSearch.value,
                typeSort = flowType.value,
                sortMode = flowSearchMode.value,
            )
        }
    }

}