package dev.rodkin.domain.useCases.useCasesImpl

import dev.rodkin.domain.repositoryIntefaces.BasketRepository
import dev.rodkin.domain.useCases.GetCountBasketItemFlowUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCountBasketItemFlowUseCaseImpl @Inject constructor(
    private val basketRepository: BasketRepository
) : GetCountBasketItemFlowUseCase {
    override fun getCountFlow(id: Long): Flow<Int?> =
        basketRepository.getBasketFlow()
            .map { basketList -> basketList.filter { basketItem -> basketItem.id == id } }
            .map { finalItem -> finalItem.firstOrNull()?.count }
}