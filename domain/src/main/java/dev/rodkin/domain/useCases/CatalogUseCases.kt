package dev.rodkin.domain.useCases

import dev.rodkin.domain.entities.CatalogItem
import kotlinx.coroutines.flow.StateFlow


interface CatalogUseCases {
    val catalogList: StateFlow<List<CatalogItem>>
    val catalogTypes: StateFlow<List<String>>
    val exception: StateFlow<String>
    suspend fun getCatalogList()
}