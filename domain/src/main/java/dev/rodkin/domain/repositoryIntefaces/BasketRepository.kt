package dev.rodkin.domain.repositoryIntefaces

import dev.rodkin.domain.entities.BasketItem

interface BasketRepository {
    suspend fun insertBasketItem(item: BasketItem)
    suspend fun deleteBasketItem(id: Long)
    suspend fun updateBasketItem(item: BasketItem)
}