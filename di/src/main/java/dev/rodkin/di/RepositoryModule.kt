package dev.rodkin.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.rodkin.data.repository.CatalogRepositoryImpl
import dev.rodkin.domain.repositoryIntefaces.CatalogRepository

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {


    @Binds
    fun bindCatalogRepository(
        catalogRepositoryImpl: CatalogRepositoryImpl
    ): CatalogRepository

    /*companion object {
        @Provides
        @Named("IO")
        fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO
    }*/
}