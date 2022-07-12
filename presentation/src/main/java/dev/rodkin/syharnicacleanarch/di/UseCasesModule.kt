package dev.rodkin.syharnicacleanarch.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.rodkin.domain.useCases.*
import dev.rodkin.domain.useCases.useCasesImpl.*


@Module
@InstallIn(ViewModelComponent::class)
interface UseCasesModule {

    @Binds
    fun bindGetBasketItemFlowUseCaseImpl(getBasketItemFlowUseCaseImpl: GetBasketItemFlowUseCaseImpl): GetBasketItemFlowUseCase

    @Binds
    fun bindGetCatalogItemsListUseCaseImpl(getCatalogItemsListUseCaseImpl: GetCatalogItemsListUseCaseImpl): GetCatalogItemsListUseCase

    @Binds
    fun bindGetCatalogItemTypeUseCaseImpl(getCatalogItemTypeUseCaseImpl: GetCatalogItemTypeUseCaseImpl): GetCatalogItemTypeUseCase

    @Binds
    fun bindSortCatalogItemTypeUseCaseImpl(sortCatalogItemTypeUseCaseImpl: SortCatalogItemTypeUseCaseImpl): SortCatalogItemTypeUseCase

    @Binds
    fun bindUpdateBasketItemsUseCaseImpl(updateBasketItemsUseCaseImpl: UpdateBasketItemsUseCaseImpl): UpdateBasketItemsUseCase

    @Binds
    fun getCountBasketItemFlowUseCaseImpl(getCountBasketItemFlowUseCaseImpl: GetCountBasketItemFlowUseCaseImpl): GetCountBasketItemFlowUseCase

    @Binds
    fun GetCountAllBasketItemsFlowUseCaseImpl(getCountAllBasketItemsFlowUseCaseImpl: GetCountAllBasketItemsFlowUseCaseImpl): GetCountAllBasketItemsFlowUseCase

    @Binds
    fun getCatalogItemFromIdUseCaseImpl(updateBasketItemsUseCaseImpl: GetCatalogItemFromIdUseCaseImpl): GetCatalogItemFromIdUseCase

    @Binds
    fun registerUserUseCaseImpl(registerUserUseCaseImpl: RegisterUserUseCaseImpl): RegisterUserUseCase
}
