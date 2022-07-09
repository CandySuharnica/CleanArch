package dev.rodkin.data.repository

import dev.rodkin.data.sources.CatalogSource
import dev.rodkin.domain.entities.ListCatalogItems
import dev.rodkin.domain.repositoryIntefaces.CatalogRepository
import dev.rodkin.domain.utils.Response
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.net.ConnectException
import javax.inject.Inject
import javax.inject.Named

class CatalogRepositoryImpl @Inject constructor(
    private val catalogSource: CatalogSource,
    @Named("IO") private val coroutineDispatcher: CoroutineDispatcher
) : CatalogRepository {

    override suspend fun getCatalogListFromRemove(): Response<ListCatalogItems> =
        withContext(coroutineDispatcher) {
            try {
                val response = catalogSource.getCatalogList()
                val body = response.body() ?: ListCatalogItems(emptyList())
                return@withContext if (response.isSuccessful) Response.Success(body)
                else Response.Error(body, response.errorBody().toString())
            } catch (e: ConnectException) {
                return@withContext Response.Error(ListCatalogItems(emptyList()), e.message ?: "")
            }
        }
}