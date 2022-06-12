package dev.rodkin.syharnicacleanarch.presenters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rodkin.domain.entities.CatalogItem
import dev.rodkin.domain.useCases.CatalogUseCases
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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




    fun getCatalogList() {
        viewModelScope.launch {
            useCase.getCatalogList()
            val catalogList = useCase.catalogList.value
            _catalogList.emit(catalogList)
            val catalogTypes = useCase.catalogTypes.value
            _catalogTypes.emit(catalogTypes)
        }
    }

}