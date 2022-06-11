package dev.rodkin.data.network

import dev.rodkin.domain.entities.CatalogItem
import retrofit2.Call
import retrofit2.http.GET

interface CatalogRemoteService {

    @GET("https://gitcdn.link/cdn/CandySuharnica/json-catalog/main/catalog.json")
    suspend fun getCatalogList() : Call<List<CatalogItem>>
}