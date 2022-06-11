package dev.rodkin.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.rodkin.data.network.CatalogRemoveService
import dev.rodkin.data.repository.CatalogRepositoryImpl
import dev.rodkin.domain.repositoryIntefaces.CatalogRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Named("IO")
    fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    fun provideCatalogRepository(
        catalogApi: CatalogRemoveService,
        @Named("IO") dispatcher: CoroutineDispatcher
    ): CatalogRepository =
        CatalogRepositoryImpl(catalogApi, dispatcher)
}