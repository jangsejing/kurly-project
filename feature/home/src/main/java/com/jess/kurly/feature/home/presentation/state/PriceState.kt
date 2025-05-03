package com.jess.kurly.feature.home.presentation.state

import androidx.compose.runtime.Stable

/**
 * @property discountRate 할인율
 * @property originalPrice 원가
 * @property sellingPrice 판매가
 */
@Stable
data class PriceState(
    val sellingPrice: Int,
    val discountRate: Int? = null,
    val originalPrice: Int? = null,
) {
    companion object {

        fun initial(
            sellingPrice: Int = 0,
            discountRate: Int? = null,
            originalPrice: Int? = null,
        ) = PriceState(
            sellingPrice = sellingPrice,
            discountRate = discountRate,
            originalPrice = originalPrice,
        )
    }
}