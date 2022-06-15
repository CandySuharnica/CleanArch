package dev.rodkin.syharnicacleanarch.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.rodkin.data.localDB.AppDatabase
import dev.rodkin.data.localDB.BasketDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): AppDatabase {
        return Room.databaseBuilder(
            appContext,
            AppDatabase::class.java,
            "AppDb"
        ).build()
    }

    @Provides
    fun provideChannelDao(appDatabase: AppDatabase): BasketDao {
        return appDatabase.basketDao()
    }

}