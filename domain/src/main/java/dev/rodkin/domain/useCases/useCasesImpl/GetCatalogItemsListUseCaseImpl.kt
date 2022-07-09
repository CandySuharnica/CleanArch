package dev.rodkin.domain.useCases.useCasesImpl

import dev.rodkin.domain.entities.CatalogItem
import dev.rodkin.domain.entities.ListCatalogItems
import dev.rodkin.domain.repositoryIntefaces.CatalogRepository
import dev.rodkin.domain.useCases.GetCatalogItemsListUseCase
import dev.rodkin.domain.utils.Response
import javax.inject.Inject

class GetCatalogItemsListUseCaseImpl @Inject constructor(
    private val catalogRepository: CatalogRepository,
) : GetCatalogItemsListUseCase {
    override suspend fun getCatalogListResponse(): Response<List<CatalogItem>> =
        when (val response = catalogRepository.getCatalogListFromRemove()) {
            is Response.Success<ListCatalogItems> -> response.mapData { it.data }
            is Response.Error<ListCatalogItems> -> response.mapData { it.data }
        }
}