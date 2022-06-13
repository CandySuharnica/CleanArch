package dev.rodkin.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BasketItem(
    @PrimaryKey val id: Long,
    val count: Int,
    val imgUrl: String,
    val label: String,
    val priceSale: Double,
    val weight: Int
)