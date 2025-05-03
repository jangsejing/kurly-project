package com.jess.kurly.feature.home.presentation.state

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Stable
sealed interface HeartState {

    @Immutable
    data object On : HeartState

    @Immutable
    data object Off : HeartState
}