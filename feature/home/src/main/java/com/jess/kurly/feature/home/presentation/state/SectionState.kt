package com.jess.kurly.feature.home.presentation.state

import androidx.compose.runtime.Stable

@Stable
data class SectionState(
    val id: Long,
    val title: String?,
    val orientation: OrientationState,
    val products: List<ProductState>,
) {
    companion object {

        fun initial(
            id: Long = 0,
            title: String? = null,
            orientation: OrientationState = OrientationState.Vertical,
            products: List<ProductState> = emptyList()
        ) = SectionState(
            id = id,
            title = title,
            orientation = orientation,
            products = products,
        )
    }
}

