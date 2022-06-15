package dev.rodkin.domain.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
data class ListCatalogItems(
    @Json(name = "catalog")
    val data: List<CatalogItem>
)

@Entity
@JsonClass(generateAdapter = true)
data class CatalogItem(
    @PrimaryKey val id: Long = 0,
    @Json(name = "name")
    val label: String = "",
    val type: String = "", //for filter in the groups
    val weight: Int = 0,
    val imgUrl: List<String> = emptyList(),
    val price: Double = 0.0,
    val priceSale: Double = 0.0,
    var likes: Int = 0,
    var about: String = "",
    @Json(name = "product_composition")
    var productComposition: String = "",
    var calorie: String = "",
    var carbohydrates: String = "",
    var fats: String = "",
    var protein: String = "",
    //var isLiked: Boolean = false
) {
    fun mapToBasketItem(count: Int): BasketItem =
        BasketItem(
            id = id,
            count = count,
            imgUrl = imgUrl[0],
            label = label,
            priceSale = priceSale,
            weight = weight
        )
}
