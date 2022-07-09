package dev.rodkin.domain.useCases.useCasesImpl

import dev.rodkin.domain.entities.CatalogItem
import dev.rodkin.domain.useCases.GetCatalogItemTypeUseCase
import dev.rodkin.domain.useCases.InitialTypes
import javax.inject.Inject

class GetCatalogItemTypeUseCaseImpl @Inject constructor(
    all:InitialTypes.ALL,
    favorite:InitialTypes.FAVORITE
) : GetCatalogItemTypeUseCase {

    private val initialTypes = listOf(all.type, favorite.type)

    override fun getCatalogTypes(catalogList: List<CatalogItem>): List<String> =
        initialTypes + catalogList
            .distinctBy { selector -> selector.type }
            .map { it.type }

}