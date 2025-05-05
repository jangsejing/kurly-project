package com.jess.kurly.feature.home.presentation.state

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import kotlinx.collections.immutable.PersistentList

@Stable
internal sealed interface SectionState {

    val id: Int? get() = null

    @Stable
    data class Title(
        val title: String?,
    ) : SectionState

    @Stable
    data class Grid(
        override val id: Int?,
        val products: PersistentList<ProductState>,
    ) : SectionState

    @Stable
    data class Horizontal(
        override val id: Int?,
        val products: PersistentList<ProductState>,
    ) : SectionState

    @Stable
    data class Vertical(
        override val id: Int?,
        val product: ProductState,
    ) : SectionState

    @Immutable
    data object Divider : SectionState

}