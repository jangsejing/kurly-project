package com.jess.kurly.feature.home.presentation.state

import androidx.compose.runtime.Stable
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@Stable
data class SectionState(
    val id: Int,
    val title: String?,
    val orientation: OrientationState,
    val products: PersistentList<ProductState>,
) {
    companion object {

        fun initial(
            id: Int = 0,
            title: String? = null,
            orientation: OrientationState = OrientationState.Vertical,
            products: PersistentList<ProductState> = persistentListOf()
        ) = SectionState(
            id = id,
            title = title,
            orientation = orientation,
            products = products,
        )
    }
}

