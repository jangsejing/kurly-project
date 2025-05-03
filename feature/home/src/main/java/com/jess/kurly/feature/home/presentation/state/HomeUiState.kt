package com.jess.kurly.feature.home.presentation.state

import androidx.compose.runtime.Stable

@Stable
data class HomeUiState(
    val isRefreshing: Boolean,
    val sections: List<SectionState>
) {

    companion object {

        fun initial(
            isRefreshing: Boolean = false,
            sections: List<SectionState> = emptyList(),
        ) = HomeUiState(
            isRefreshing = isRefreshing,
            sections = sections,
        )
    }
}
