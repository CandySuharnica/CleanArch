package dev.rodkin.domain.useCases.useCasesImpl

import dev.rodkin.domain.entities.CatalogItem
import dev.rodkin.domain.repositoryIntefaces.CatalogRepository
import dev.rodkin.domain.useCases.CatalogUseCases
import dev.rodkin.domain.utils.ResponseCatalog
import javax.inject.Inject

class CatalogUseCasesImpl constructor(
    private val catalogRepository: CatalogRepository
) : CatalogUseCases {

    override suspend fun getCatalogList(): List<CatalogItem> {
        val response = catalogRepository.getCatalogListFromRemove()

        when (response) {
            is ResponseCatalog.Success -> return response.data
            is ResponseCatalog.Error -> TODO()
        }
    }
}