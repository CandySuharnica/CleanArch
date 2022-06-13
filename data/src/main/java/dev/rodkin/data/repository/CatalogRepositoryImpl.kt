package dev.rodkin.data.repository

import dev.rodkin.data.sources.CatalogSource
import dev.rodkin.domain.entities.CatalogItem
import dev.rodkin.domain.entities.ListCatalogItems
import dev.rodkin.domain.repositoryIntefaces.CatalogRepository
import dev.rodkin.domain.utils.Response
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Named

class CatalogRepositoryImpl @Inject constructor(
    private val catalogSource: CatalogSource,
    @Named("IO") private val coroutineDispatcher: CoroutineDispatcher
) : CatalogRepository {

    override suspend fun getCatalogListFromRemove(): Response<ListCatalogItems> {
        return withContext(coroutineDispatcher) {
            val response = catalogSource.getCatalogList()
            if (response.isSuccessful) {
                val body = response.body() ?: ListCatalogItems(emptyList())
                Response.Success(body)
            } else
                Response.Error(response.errorBody().toString())
        }
    }
}