package dev.rodkin.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.rodkin.data.network.CatalogRemoveService
import dev.rodkin.data.utils.Constants
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    @Named("baseUrl")
    fun baseUrlProvider() = Constants.BASE_URL

    @Provides
    @Singleton
    fun retrofitProvider(@Named("baseUrl") baseUrl: String, okHttpClient: OkHttpClient): Retrofit =
        Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            //.addInterceptor(CatalogApiInterceptor)
            .build()
    }


    @Provides
    @Singleton
    fun providesApiService(retrofit: Retrofit): CatalogRemoveService =
        retrofit.create(CatalogRemoveService::class.java)
}