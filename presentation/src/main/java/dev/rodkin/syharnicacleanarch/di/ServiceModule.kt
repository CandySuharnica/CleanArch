package dev.rodkin.syharnicacleanarch.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.rodkin.data.network.CatalogRemoteService
import dev.rodkin.data.sources.CatalogSource
import dev.rodkin.data.sources.CatalogSourceImpl
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
interface ServiceModule {

    @Binds
    fun bindCatalogSource(sourceImpl: CatalogSourceImpl): CatalogSource
}