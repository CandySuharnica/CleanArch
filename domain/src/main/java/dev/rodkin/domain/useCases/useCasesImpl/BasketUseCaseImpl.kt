package dev.rodkin.domain.useCases.useCasesImpl

import dev.rodkin.domain.entities.BasketItem
import dev.rodkin.domain.entities.CatalogItem
import dev.rodkin.domain.repositoryIntefaces.BasketRepository
import dev.rodkin.domain.useCases.BasketUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class BasketUseCaseImpl @Inject constructor(
    private val basketRepository: BasketRepository
) : BasketUseCase {

    private val _basketList = MutableStateFlow(emptyList<BasketItem>())
    override val basketList: StateFlow<List<BasketItem>> = _basketList

    private val _exception = MutableStateFlow("")
    override val exception: StateFlow<String> = _exception

    override suspend fun getBasketList() {
        basketRepository.getBasketFlow().collect { basketList ->
            _basketList.emit(basketList)
        }
    }

    override suspend fun updateBasketItems(item: CatalogItem, onBasketMode: OnBasketMode) {
        val existItem = _basketList.value.find { item.id == it.id }
        when (onBasketMode) {
            OnBasketMode.ADD -> {
                if (existItem != null) {
                    val basketItem = item.mapToBasketItem(existItem.count + 1)
                    updateBasketItem(basketItem)
                } else {
                    val basketItem = item.mapToBasketItem(1)
                    insertBasketItem(basketItem)
                }
            }
            OnBasketMode.REMOVE -> {
                if (existItem != null) {
                    if (existItem.count > 0) {
                        val basketItem = item.mapToBasketItem(existItem.count - 1)
                        updateBasketItem(basketItem)
                    } else {
                        val basketItem = item.mapToBasketItem(1)
                        deleteBasketItem(basketItem)
                    }
                } else {
                    _exception.emit("Упс вы пытаетесь удалить несуществующий элемент")
                }
            }
        }

    }

    override suspend fun updateBasketItems(item: BasketItem, onBasketMode: OnBasketMode) {
        val existItem = _basketList.value.find { item.id == it.id }
        when (onBasketMode) {
            OnBasketMode.ADD -> {
                if (existItem != null) {
                    val basketItem = item.copy(count = item.count + 1)
                    updateBasketItem(basketItem)
                } else {
                    _exception.emit("Упс нельзя из корзины добавлять новый, ранее не существующий элемент")
                }
            }
            OnBasketMode.REMOVE -> {
                if (existItem != null) {
                    if (existItem.count > 1) {
                        val basketItem = item.copy(count = item.count - 1)
                        updateBasketItem(basketItem)
                    } else {
                        deleteBasketItem(item)
                    }
                } else {
                    _exception.emit("Упс вы пытаетесь удалить несуществующий элемент")
                }
            }
        }
    }

    private suspend fun insertBasketItem(item: BasketItem) {
        basketRepository.insertBasketItem(item)
    }

    private suspend fun deleteBasketItem(item: BasketItem) {
        basketRepository.deleteBasketItem(item)
    }

    private suspend fun updateBasketItem(item: BasketItem) {
        basketRepository.updateBasketItem(item)
    }
}

enum class OnBasketMode {
    ADD, REMOVE
}