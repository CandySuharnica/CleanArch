package dev.rodkin.domain.useCases.useCasesImpl

import dev.rodkin.domain.entities.CatalogItem
import dev.rodkin.domain.useCases.InitialTypes
import dev.rodkin.domain.useCases.SortCatalogItemTypeUseCase
import dev.rodkin.domain.useCases.SortMode
import javax.inject.Inject

class SortCatalogItemTypeUseCaseImpl @Inject constructor(
    all: InitialTypes.ALL,
    favorite: InitialTypes.FAVORITE
) : SortCatalogItemTypeUseCase {

    val all = all
    val favorite = favorite

    override fun sortCatalogList(
        unsortedList: List<CatalogItem>,
        searchSort: String,
        typeSort: String,
        sortMode: SortMode
    ): List<CatalogItem> =
        unsortedList.filter { searchFilter ->
            if (searchSort != "") searchFilter.label.contains(searchSort, ignoreCase = true)
            else true
        }.filter { typeFilter ->
            when (typeSort) {
                all.type -> true
                favorite.type -> true
                else -> typeFilter.type == typeSort
            }
        }.filter { sortModeFilter ->
            when (sortMode) {
                SortMode.NONE -> true
            }
        }
}