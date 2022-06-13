package dev.rodkin.data.localDB

import androidx.room.Database
import androidx.room.RoomDatabase
import dev.rodkin.data.utils.Constants
import dev.rodkin.domain.entities.BasketItem

@Database(entities = [BasketItem::class], version = Constants.DbVersion)
abstract class AppDatabase : RoomDatabase() {
    abstract fun basketDao():BasketDao
}