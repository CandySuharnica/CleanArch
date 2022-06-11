package dev.rodkin.data.sources

import dev.rodkin.data.network.CatalogRemoteService
import dev.rodkin.domain.entities.CatalogItem
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.awaitResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatalogSourceImpl @Inject constructor(
    private val config: Retrofit
) : CatalogSource {

    private val catalogApi = config.create(CatalogRemoteService::class.java)

    override suspend fun getCatalogList(): Response<List<CatalogItem>> {
        return catalogApi.getCatalogList().awaitResponse()
    }

}