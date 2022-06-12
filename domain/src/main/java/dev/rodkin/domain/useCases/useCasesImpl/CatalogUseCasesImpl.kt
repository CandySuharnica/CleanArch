package dev.rodkin.domain.useCases.useCasesImpl

import dev.rodkin.domain.entities.CatalogItem
import dev.rodkin.domain.entities.ListCatalogItems
import dev.rodkin.domain.repositoryIntefaces.CatalogRepository
import dev.rodkin.domain.useCases.CatalogUseCases
import dev.rodkin.domain.utils.Response
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class CatalogUseCasesImpl @Inject constructor(
    private val catalogRepository: CatalogRepository,
) : CatalogUseCases {

    private val _catalogList = MutableStateFlow(emptyList<CatalogItem>())
    override val catalogList: StateFlow<List<CatalogItem>> = _catalogList

    private val _catalogTypes = MutableStateFlow(emptyList<String>())
    override val catalogTypes: StateFlow<List<String>> = _catalogTypes

    private val _exception = MutableStateFlow("")
    override val exception: StateFlow<String> = _exception

    override suspend fun getCatalogList() {
        val response = catalogRepository.getCatalogListFromRemove()

        when (response) {
            is Response.Success<out ListCatalogItems> -> {
                with(response.data) {
                    _catalogList.emit(data)
                    getCatalogTypes(data)
                }
            }
            is Response.Error<*> -> _exception.emit(response.exception.toString())
        }
    }

    private suspend fun getCatalogTypes(catalogList: List<CatalogItem>) {
        val listTypes = catalogList
            .distinctBy { selector -> selector.type }
            .map { it.type }
        _catalogTypes.emit(initialTypes + listTypes)
    }

    companion object{
        val initialTypes = listOf(InitialTypes.ALL.name, InitialTypes.FAVORITE.name)
    }
}

enum class InitialTypes(name: String) {
    ALL("Все"),FAVORITE("Любимые")
}