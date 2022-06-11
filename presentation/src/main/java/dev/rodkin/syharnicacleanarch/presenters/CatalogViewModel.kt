package dev.rodkin.syharnicacleanarch.presenters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rodkin.domain.entities.CatalogItem
import dev.rodkin.domain.repositoryIntefaces.CatalogRepository
import dev.rodkin.domain.useCases.CatalogUseCases
import dev.rodkin.domain.useCases.useCasesImpl.CatalogUseCasesImpl
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatalogViewModel @Inject constructor(
    private val catalogRepository: CatalogRepository
   //private val catalogUseCases: CatalogUseCases
) : ViewModel() {

    val useCase = CatalogUseCasesImpl(catalogRepository)
    private val _catalogList = MutableStateFlow(emptyList<CatalogItem>())
    val catalogList: StateFlow<List<CatalogItem>> = _catalogList


    fun getCatalogList() {
        viewModelScope.launch {
            /*val catalogList = useCase.getCatalogList()
            _catalogList.emit(catalogList)*/
        }
    }
}