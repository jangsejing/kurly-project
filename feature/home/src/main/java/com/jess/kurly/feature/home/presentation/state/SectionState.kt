package com.jess.kurly.feature.home.presentation.state

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import kotlinx.collections.immutable.PersistentList

@Stable
internal sealed interface SectionState {

    @Stable
    data class Title(
        val title: String?,
    ) : SectionState

    @Stable
    data class Grid(
        val id: Int,
        val products: PersistentList<ProductState>,
    ) : SectionState

    @Stable
    data class Horizontal(
        val id: Int,
        val products: PersistentList<ProductState>,
    ) : SectionState

    @Stable
    data class Vertical(
        val id: Int,
        val product: ProductState,
    ) : SectionState

    @Immutable
    data object Divider : SectionState
}
