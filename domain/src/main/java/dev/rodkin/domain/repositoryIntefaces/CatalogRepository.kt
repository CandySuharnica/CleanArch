package dev.rodkin.domain.repositoryIntefaces

import dev.rodkin.domain.utils.ResponseCatalog

interface CatalogRepository {
    suspend fun getCatalogListFromRemove(): ResponseCatalog
}