package dev.rodkin.syharnicacleanarch.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rodkin.domain.entities.CatalogItem
import dev.rodkin.domain.useCases.GetCatalogItemFromIdUseCase
import dev.rodkin.domain.useCases.GetCountBasketItemFlowUseCase
import dev.rodkin.domain.useCases.OnBasketMode
import dev.rodkin.domain.useCases.UpdateBasketItemsUseCase
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailCatalogViewModel @Inject constructor(
    private val getCountUseCase: GetCountBasketItemFlowUseCase,
    private val getCatalogItemUseCase: GetCatalogItemFromIdUseCase,
    private val updateBasketUseCase: UpdateBasketItemsUseCase
) : ViewModel() {

    private val _catalogItem = MutableStateFlow<CatalogItem?>(null)
    val catalogItem: StateFlow<CatalogItem?> = _catalogItem

    fun getItemFromId(itemId: Long) =
        viewModelScope.launch { _catalogItem.value = getCatalogItemUseCase.getCatalogItem(itemId) }

    fun getItemCountFromId(itemId: Long): Flow<Int?> =
        getCountUseCase.getCountFlow(itemId)

    fun updateBasket(item: CatalogItem, onBasketMode: OnBasketMode) {
        viewModelScope.launch {
            updateBasketUseCase.updateBasketItems(item, onBasketMode)
        }
    }
}