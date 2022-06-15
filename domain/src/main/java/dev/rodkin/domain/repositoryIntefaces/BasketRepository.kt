package dev.rodkin.domain.repositoryIntefaces

import dev.rodkin.domain.entities.BasketItem
import kotlinx.coroutines.flow.Flow

interface BasketRepository {
    fun getBasketFlow(): Flow<List<BasketItem>>
    suspend fun insertBasketItem(item: BasketItem)
    suspend fun deleteBasketItem(item: BasketItem)
    suspend fun updateBasketItem(item: BasketItem)
}