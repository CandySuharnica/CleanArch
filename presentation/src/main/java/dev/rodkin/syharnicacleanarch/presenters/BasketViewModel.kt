package dev.rodkin.syharnicacleanarch.presenters

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.rodkin.domain.entities.BasketItem
import dev.rodkin.domain.entities.CatalogItem
import dev.rodkin.domain.useCases.BasketUseCase
import dev.rodkin.domain.useCases.useCasesImpl.OnBasketMode
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val basketUseCase: BasketUseCase
) : ViewModel() {

    private val _basketList = MutableStateFlow(emptyList<BasketItem>())
    val basketList: StateFlow<List<BasketItem>> = _basketList

    private val _exception = MutableStateFlow("")
    val exception: StateFlow<String> = _exception

    init {
        viewModelScope.apply {
            launch {
                //init basket list
                basketUseCase.getBasketList()
            }
            launch {
                basketUseCase.basketList.collect { basketList ->
                    _basketList.value = basketList
                }
            }
        }
    }

    fun updateBasket(item: CatalogItem, onBasketMode: OnBasketMode) {
        viewModelScope.launch {
            basketUseCase.updateBasketItems(item, onBasketMode)
        }
    }

    fun updateBasket(item: BasketItem, onBasketMode: OnBasketMode) {
        viewModelScope.launch {
            basketUseCase.updateBasketItems(item, onBasketMode)
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