package dev.rodkin.data.repository

import dev.rodkin.data.network.CatalogRemoteService
import dev.rodkin.data.sources.CatalogSource
import dev.rodkin.domain.utils.ResponseCatalog
import dev.rodkin.domain.repositoryIntefaces.CatalogRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.awaitResponse
import javax.inject.Inject

class CatalogRepositoryImpl @Inject constructor(
    private val catalogSource: CatalogSource,
    //@Named("IO") private val coroutineDispatcher: CoroutineDispatcher
):CatalogRepository {

    override suspend fun getCatalogListFromRemove(): ResponseCatalog {
        return withContext(Dispatchers.IO) {
            val response = catalogSource.getCatalogList()
            if (response.isSuccessful) {
                val body = response.body() ?: emptyList()
                ResponseCatalog.Success(body)
            } else
                ResponseCatalog.Error(response.errorBody())
        }
    }
}