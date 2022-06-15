package dev.rodkin.data.localDB

import androidx.room.*
import dev.rodkin.domain.entities.BasketItem
import kotlinx.coroutines.flow.Flow

@Dao
interface BasketDao {

    @Query("SELECT * FROM basketItem")
    fun getBasketList(): Flow<List<BasketItem>>
/*
    @Query("DELETE FROM basketItem")
    fun deleteTable()

    @Query("SELECT SUM(count) FROM basketItem")
    fun getCount() : Int*/

   /* @Query("SELECT SUM(price_sum) FROM basketItem")
    fun getAmountPrice() : Double

    @Query("SELECT * FROM basketItem WHERE name LIKE :query")
    fun getListFromQuery(query: String): List<BasketDbEntity>

    @Query("SELECT * FROM basketItem WHERE product_id = :id")
    fun getItemFromId(id: Int) : BasketDbEntity?

    @Query("SELECT count FROM basketItem WHERE product_id = :id")
    fun getCountFromId(id: Int) : Int*/

    @Insert
    fun insert(item: BasketItem)

    @Update
    fun update(item: BasketItem)

    @Delete
    fun delete(item: BasketItem)
}