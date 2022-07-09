package dev.rodkin.domain.useCases

import dev.rodkin.domain.entities.CatalogItem
import dev.rodkin.domain.utils.Response

interface GetCatalogItemsListUseCase {
   suspend fun getCatalogListResponse(): Response<List<CatalogItem>>
}