package dev.rodkin.data.sources

import dev.rodkin.domain.entities.CatalogItem
import retrofit2.Response

interface CatalogSource {
    suspend fun getCatalogList(): Response<List<CatalogItem>>
}