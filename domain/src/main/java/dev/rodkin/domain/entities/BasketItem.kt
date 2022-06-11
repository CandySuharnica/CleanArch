package dev.rodkin.domain.entities

data class BasketItem(
    val id: Long,
    val count: Int,
    val imgUrl: List<String>,
    val label: String,
    val priceSale: Double,
    val weight: Int
)