package dev.rodkin.domain.useCases

import dev.rodkin.domain.entities.BasketItem
import dev.rodkin.domain.entities.CatalogItem
import kotlinx.coroutines.flow.Flow

interface GetBasketItemFlowUseCase {
    fun getBasketFlow(): Flow<List<BasketItem>>
}

interface GetCountBasketItemFlowUseCase {
    fun getCountFlow(id: Long): Flow<Int?>
}

interface GetCountAllBasketItemsFlowUseCase {
    fun getCountFlow(): Flow<Int>
}

interface GetCatalogItemFromIdUseCase {
    suspend fun getCatalogItem(id: Long): CatalogItem?
}

interface UpdateBasketItemsUseCase {
    suspend fun updateBasketItems(item: CatalogItem, onBasketMode: OnBasketMode)
    suspend fun updateBasketItems(item: BasketItem, onBasketMode: OnBasketMode)
}

enum class OnBasketMode {
    ADD, REMOVE
}