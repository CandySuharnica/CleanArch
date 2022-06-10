package dev.rodkin.domain.utils

import dev.rodkin.domain.entities.CatalogItem
import okhttp3.ResponseBody

sealed class ResponseCatalog {
    data class Success(val data: List<CatalogItem>) : ResponseCatalog()
    data class Error(val exception: ResponseBody?) : ResponseCatalog()
}