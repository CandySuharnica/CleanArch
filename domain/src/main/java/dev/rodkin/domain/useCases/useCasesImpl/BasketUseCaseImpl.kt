package dev.rodkin.domain.useCases.useCasesImpl

import dev.rodkin.domain.entities.BasketItem
import dev.rodkin.domain.repositoryIntefaces.BasketRepository
import dev.rodkin.domain.useCases.BasketUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class BasketUseCaseImpl @Inject constructor(
    private val basketRepository: BasketRepository
) : BasketUseCase {

    private val _basketList = MutableStateFlow(emptyList<BasketItem>())
    override val basketList: StateFlow<List<BasketItem>> = _basketList

    private val _exception = MutableStateFlow("")
    override val exception: StateFlow<String> = _exception


    override suspend fun insertBasketItem(item: BasketItem) {
        basketRepository.insertBasketItem(item)
    }

    override suspend fun deleteBasketItem(id: Long) {
        basketRepository.deleteBasketItem(id)
    }

    override suspend fun updateBasketItem(item: BasketItem) {
        basketRepository.updateBasketItem(item)
    }
}