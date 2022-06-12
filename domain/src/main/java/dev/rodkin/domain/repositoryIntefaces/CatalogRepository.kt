package dev.rodkin.domain.repositoryIntefaces

import dev.rodkin.domain.entities.ListCatalogItems
import dev.rodkin.domain.utils.Response

interface CatalogRepository {
    suspend fun getCatalogListFromRemove(): Response<out ListCatalogItems>
}