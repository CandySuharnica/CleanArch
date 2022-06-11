package dev.rodkin.domain.useCases

import dev.rodkin.domain.entities.CatalogItem

interface CatalogUseCases {
    suspend fun getCatalogList(): List<CatalogItem>
}