package dev.rodkin.syharnicacleanarch.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dev.rodkin.domain.useCases.BasketUseCase
import dev.rodkin.domain.useCases.CatalogUseCases
import dev.rodkin.domain.useCases.useCasesImpl.BasketUseCaseImpl
import dev.rodkin.domain.useCases.useCasesImpl.CatalogUseCasesImpl


@Module
@InstallIn(ViewModelComponent::class)
interface UseCasesModule {

    @Binds
    fun bindCatalogUseCase(catalogRepositoryImpl: CatalogUseCasesImpl): CatalogUseCases

    @Binds
    fun bindBasketUseCase(basketUseCaseImpl: BasketUseCaseImpl): BasketUseCase
}
