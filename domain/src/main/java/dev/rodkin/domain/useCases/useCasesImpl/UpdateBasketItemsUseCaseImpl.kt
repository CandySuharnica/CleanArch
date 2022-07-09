package dev.rodkin.domain.useCases.useCasesImpl

import dev.rodkin.domain.entities.BasketItem
import dev.rodkin.domain.entities.CatalogItem
import dev.rodkin.domain.repositoryIntefaces.BasketRepository
import dev.rodkin.domain.useCases.GetBasketItemFlowUseCase
import dev.rodkin.domain.useCases.OnBasketMode
import dev.rodkin.domain.useCases.UpdateBasketItemsUseCase
import kotlinx.coroutines.flow.last
import javax.inject.Inject

class UpdateBasketItemsUseCaseImpl @Inject constructor(
    private val basketRepository: BasketRepository,
    private val basketFlow: GetBasketItemFlowUseCase
) : UpdateBasketItemsUseCase {

    private val basketItems = basketFlow.getBasketFlow()

    override suspend fun updateBasketItems(item: CatalogItem, onBasketMode: OnBasketMode) {
        val existItem = basketItems.last().find { item.id == it.id }
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
                    throw Exception("Упс вы пытаетесь удалить несуществующий элемент")
                }
            }
        }
    }

    override suspend fun updateBasketItems(item: BasketItem, onBasketMode: OnBasketMode) {
        val existItem = basketItems.last().find { item.id == it.id }
        when (onBasketMode) {
            OnBasketMode.ADD -> {
                if (existItem != null) {
                    val basketItem = item.copy(count = item.count + 1)
                    updateBasketItem(basketItem)
                } else {
                    throw Exception("Упс нельзя из корзины добавлять новый, ранее не существующий элемент")
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
                    throw Exception("Упс вы пытаетесь удалить несуществующий элемент")
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