package dev.rodkin.domain.useCases

import dev.rodkin.domain.entities.BasketItem
import kotlinx.coroutines.flow.StateFlow

interface BasketUseCase {

    val basketList: StateFlow<List<BasketItem>>
    val exception: StateFlow<String>

    suspend fun insertBasketItem(item: BasketItem)
    suspend fun deleteBasketItem(id: Long)
    suspend fun updateBasketItem(item: BasketItem)
}