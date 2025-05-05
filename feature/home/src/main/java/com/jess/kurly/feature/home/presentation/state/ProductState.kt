package com.jess.kurly.feature.home.presentation.state

import androidx.compose.runtime.Stable

@Stable
internal data class ProductState(
    val id: Int?,
    val title: String?,
    val heart: HeartState?,
    val image: String?,
    val price: PriceState?,
)

