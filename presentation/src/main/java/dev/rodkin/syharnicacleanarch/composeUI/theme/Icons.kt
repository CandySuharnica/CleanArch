package dev.rodkin.syharnicacleanarch.composeUI.theme

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import dev.rodkin.syharnicacleanarch.R


sealed class Icons(
    @StringRes val description: Int, @DrawableRes val image: Int
) {

    object Search : Icons(
        R.string.search,
        R.drawable.ic_search
    )

    object Sort : Icons(
        R.string.sort,
        R.drawable.ic_sort
    )

    object Profile : Icons(
        R.string.profile,
        R.drawable.ic_profile
    )

    object Catalog : Icons(
        R.string.catalog,
        R.drawable.ic_catalog
    )

    object Basket : Icons(
        R.string.basket,
        R.drawable.ic_basket
    )

    object Edit : Icons(
        R.string.edit,
        R.drawable.ic_edit
    )

    object Smile : Icons(
        R.string.smile,
        R.drawable.ic_smile
    )

    object BigSmile : Icons(
        R.string.smile,
        R.drawable.ic_big_smile
    )

    object Plus : Icons(
        R.string.plus,
        R.drawable.ic_plus
    )

    object BigPlus : Icons(
        R.string.plus,
        R.drawable.ic_big_plus
    )

    object ArrowBack : Icons(
        R.string.arrow_back,
        R.drawable.ic_arrow_back
    )

    object Share : Icons(
        R.string.share,
        R.drawable.ic_share
    )

    object Minus : Icons(
        R.string.minus,
        R.drawable.ic_minus
    )

    object SmallMinus : Icons(
        R.string.minus,
        R.drawable.ic_small_minus
    )

    object Label : Icons(
        R.string.syharnica,
        R.drawable.ic_lable
    )

}

