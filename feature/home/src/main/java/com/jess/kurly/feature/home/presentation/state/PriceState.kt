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

        fun zero(
            sellingPrice: Int = 0,
            discountRate: Int? = 0,
            originalPrice: Int? = 0,
        ) = PriceState(
            sellingPrice = sellingPrice,
            discountRate = discountRate,
            originalPrice = originalPrice,
        )
    }
}
