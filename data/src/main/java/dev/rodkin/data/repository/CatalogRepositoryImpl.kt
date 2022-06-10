package dev.rodkin.data.repository

import dev.rodkin.data.network.CatalogRemoveService
import dev.rodkin.domain.utils.ResponseCatalog
import dev.rodkin.domain.repositoryIntefaces.CatalogRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse
import javax.inject.Inject
import javax.inject.Named

class CatalogRepositoryImpl @Inject constructor(
    private val apiCatalogService: CatalogRemoveService,
    @Named("IO") private val coroutineDispatcher: CoroutineDispatcher
):CatalogRepository {

    override suspend fun getCatalogListFromRemove(): ResponseCatalog {
        return withContext(coroutineDispatcher) {
            val response = apiCatalogService.getCatalogList().awaitResponse()
            if (response.isSuccessful) {
                val body = response.body() ?: emptyList()
                ResponseCatalog.Success(body)
            } else
                ResponseCatalog.Error(response.errorBody())
        }
    }
}