package com.jess.kurly.feature.home.presentation.state

import androidx.compose.runtime.Stable
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf

@Stable
internal data class HomeUiState(
    val isRefreshing: Boolean,
    val sections: PersistentList<SectionState>
) {

    companion object {

        fun initial(
            isRefreshing: Boolean = false,
            sections: PersistentList<SectionState> = persistentListOf(),
        ) = HomeUiState(
            isRefreshing = isRefreshing,
            sections = sections,
        )
    }
}
