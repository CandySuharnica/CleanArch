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
    private val _sortedCatalogList = MutableStateFlow(emptyList<CatalogItem>())
    override val catalogList: StateFlow<List<CatalogItem>> = _sortedCatalogList

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
            is Response.Error<*> -> _exception.emit(response.exception)
        }
    }

    private suspend fun getCatalogTypes(catalogList: List<CatalogItem>) {
        val listTypes = catalogList
            .distinctBy { selector -> selector.type }
            .map { it.type }
        _catalogTypes.emit(initialTypes + listTypes)
    }

    override suspend fun sortCatalogList(
        searchSort: String,
        typeSort: String,
        sortMode: SortMode
    ) {
        val sortedList =
            _catalogList.value.filter { searchFilter ->
                if (searchSort != "") searchFilter.label.contains(searchSort, ignoreCase = true)
                else true
            }.filter { typeFilter ->
                when (typeSort) {
                    InitialTypes.ALL.type -> true
                    InitialTypes.FAVORITE.name -> true
                    else -> typeFilter.type == typeSort
                }
            }.filter { sortModeFilter ->
                when (sortMode) {
                    SortMode.NONE -> true
                }
            }

        _sortedCatalogList.emit(sortedList)
    }

    companion object {
        val initialTypes = listOf(InitialTypes.ALL.type, InitialTypes.FAVORITE.type)
    }
}

enum class InitialTypes(val type: String) {
    ALL("Все"), FAVORITE("Любимые")
}

enum class SortMode {
    NONE
}