package dev.rodkin.domain.useCases.useCasesImpl

import dev.rodkin.domain.entities.CatalogItem
import dev.rodkin.domain.repositoryIntefaces.CatalogRepository
import dev.rodkin.domain.useCases.GetCatalogItemFromIdUseCase
import javax.inject.Inject

class GetCatalogItemFromIdUseCaseImpl @Inject constructor(
    private val catalogRepository: CatalogRepository,
) : GetCatalogItemFromIdUseCase {
    override suspend fun getCatalogItem(id: Long): CatalogItem? =
        catalogRepository.getCatalogListFromRemove().data.data.find{ it.id == id}
}