package dev.rodkin.domain.useCases.useCasesImpl

import dev.rodkin.domain.entities.BasketItem
import dev.rodkin.domain.repositoryIntefaces.BasketRepository
import dev.rodkin.domain.useCases.GetBasketItemFlowUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBasketItemFlowUseCaseImpl @Inject constructor(
    private val basketRepository: BasketRepository
) : GetBasketItemFlowUseCase {
    override fun getBasketFlow(): Flow<List<BasketItem>> =
        basketRepository.getBasketFlow()
}