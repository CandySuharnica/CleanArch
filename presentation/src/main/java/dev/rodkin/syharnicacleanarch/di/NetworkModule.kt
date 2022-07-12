package dev.rodkin.syharnicacleanarch.di

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.rodkin.data.utils.Constants
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
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
    fun retrofitProvider(@Named("baseUrl") baseUrl: String, okHttpClient: OkHttpClient/*, moshi: Moshi*/): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(MoshiConverterFactory.create())
            .baseUrl(baseUrl)
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
    @Named("IO")
    fun provideDispatcher() = Dispatchers.IO

    @Provides
    @Singleton
    fun firebaseAuth() = Firebase.auth


}