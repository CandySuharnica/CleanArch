package dev.rodkin.domain.useCases

import dev.rodkin.domain.entities.BasketItem
import dev.rodkin.domain.entities.CatalogItem
import dev.rodkin.domain.useCases.useCasesImpl.OnBasketMode
import kotlinx.coroutines.flow.StateFlow

interface BasketUseCase {

    val basketList: StateFlow<List<BasketItem>>
    val exception: StateFlow<String>

    suspend fun getBasketList()
    suspend fun updateBasketItems(item: CatalogItem, onBasketMode: OnBasketMode)
    suspend fun updateBasketItems(item: BasketItem, onBasketMode: OnBasketMode)
}