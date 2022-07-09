package dev.rodkin.domain.useCases

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import dev.rodkin.domain.R
import dev.rodkin.domain.entities.CatalogItem
import javax.inject.Inject

interface GetCatalogItemTypeUseCase {
    fun getCatalogTypes(catalogList: List<CatalogItem>): List<String>
}

interface SortCatalogItemTypeUseCase {
    fun sortCatalogList(
        unsortedList: List<CatalogItem>,
        searchSort: String,
        typeSort: String,
        sortMode: SortMode
    ): List<CatalogItem>
}


enum class SortMode {
    NONE
}

sealed class InitialTypes(var type: String) {
    class ALL @Inject constructor(@ApplicationContext context: Context) :
        InitialTypes(context.getString(R.string.all))

    class FAVORITE @Inject constructor(@ApplicationContext context: Context) :
        InitialTypes(context.getString(R.string.favorite))
}
