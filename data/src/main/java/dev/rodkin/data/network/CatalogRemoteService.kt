package dev.rodkin.data.network

import dev.rodkin.domain.entities.ListCatalogItems
import retrofit2.Call
import retrofit2.http.GET

interface CatalogRemoteService {

    @GET("catalog.json")
    fun getCatalogList() : Call<ListCatalogItems>
}