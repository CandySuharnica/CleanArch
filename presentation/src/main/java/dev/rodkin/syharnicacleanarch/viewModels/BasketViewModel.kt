package dev.rodkin.syharnicacleanarch.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rodkin.domain.entities.BasketItem
import dev.rodkin.domain.entities.CatalogItem
import dev.rodkin.domain.useCases.GetBasketItemFlowUseCase
import dev.rodkin.domain.useCases.OnBasketMode
import dev.rodkin.domain.useCases.UpdateBasketItemsUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val basketFlow: GetBasketItemFlowUseCase,
    private val updateBasketUseCase: UpdateBasketItemsUseCase
) : ViewModel() {

    private val _basketList = MutableStateFlow(emptyList<BasketItem>())
    val basketList: StateFlow<List<BasketItem>> = _basketList

    private val _exception = MutableStateFlow("")
    val exception: StateFlow<String> = _exception

    init {
        viewModelScope.launch {
            basketFlow.getBasketFlow().collect { basketList ->
                _basketList.value = basketList
            }
        }
    }

    fun updateBasket(item: BasketItem, onBasketMode: OnBasketMode) {
        viewModelScope.launch {
            updateBasketUseCase.updateBasketItems(item, onBasketMode)
        }
    }

    fun getItemCountFromId(id: Long): Flow<Int> {
        return flow {
            _basketList.collect { basketList ->
                val count = basketList.find { it.id == id }?.count ?: 0
                emit(count)
            }
        }
    }

}