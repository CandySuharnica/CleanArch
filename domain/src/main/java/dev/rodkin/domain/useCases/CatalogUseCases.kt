package dev.rodkin.domain.useCases

import dev.rodkin.domain.entities.CatalogItem
import dev.rodkin.domain.useCases.useCasesImpl.SortMode
import kotlinx.coroutines.flow.StateFlow


interface CatalogUseCases {

    val catalogList: StateFlow<List<CatalogItem>>
    val catalogTypes: StateFlow<List<String>>
    val exception: StateFlow<String>

    suspend fun getCatalogList()
    suspend fun sortCatalogList(
        searchSort: String,
        typeSort: String,
        sortMode: SortMode
    )
}