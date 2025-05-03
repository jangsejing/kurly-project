package com.jess.kurly.feature.home.presentation.state

import androidx.compose.runtime.Stable

@Stable
data class ProductState(
    val id: Long,
    val title: String?,
    val heart: HeartState?,
    val image: String?,
    val price: PriceState?,
) {

    companion object {

        fun initial(
            id: Long = 0,
            title: String? = null,
            heart: HeartState? = HeartState.Off,
            image: String? = null,
            price: PriceState? = PriceState.initial(),
        ) = ProductState(
            id = id,
            title = title,
            heart = heart,
            image = image,
            price = price,
        )
    }
}

