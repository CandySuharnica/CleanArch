package dev.rodkin.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import dev.rodkin.domain.repositoryIntefaces.CatalogRepository
import dev.rodkin.domain.useCases.CatalogUseCases
import dev.rodkin.domain.useCases.useCasesImpl.CatalogUseCasesImpl

/*@Module
@InstallIn(ViewModelComponent::class)
class UseCasesModule {
    @Provides
    fun provideCatalogUseCase(catalogRepository: CatalogRepository): CatalogUseCases =
        CatalogUseCasesImpl(catalogRepository)
}*/





/*@Module
@InstallIn(ViewModelComponent::class)
interface UseCasesModule {

    @Binds
    fun bindCatalogUseCase(catalogRepositoryImpl: CatalogUseCasesImpl): CatalogUseCases
}*/
