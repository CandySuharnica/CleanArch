package dev.rodkin.data.sources

import dev.rodkin.domain.entities.ListCatalogItems
import retrofit2.Response

interface CatalogSource {
    suspend fun getCatalogList(): Response<ListCatalogItems>
}