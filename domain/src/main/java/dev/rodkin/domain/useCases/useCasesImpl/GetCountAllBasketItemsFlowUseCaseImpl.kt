package dev.rodkin.domain.useCases.useCasesImpl

import dev.rodkin.domain.repositoryIntefaces.BasketRepository
import dev.rodkin.domain.useCases.GetCountAllBasketItemsFlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCountAllBasketItemsFlowUseCaseImpl @Inject constructor(
    private val basketRepository: BasketRepository
):GetCountAllBasketItemsFlowUseCase {
    override fun getCountFlow(): Flow<Int> =
        basketRepository.getBasketFlow().map { basketList ->
            basketList.sumOf { it.count }
        }
}