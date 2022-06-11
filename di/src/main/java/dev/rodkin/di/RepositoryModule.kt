package dev.rodkin.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.rodkin.data.repository.CatalogRepositoryImpl
import dev.rodkin.domain.repositoryIntefaces.CatalogRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideCatalogRepository(
        catalogRepositoryImpl: CatalogRepositoryImpl
    ): CatalogRepository

    companion object {
        @Provides
        @Named("IO")
        fun provideIODispatcher(): CoroutineDispatcher = Dispatchers.IO
    }
}