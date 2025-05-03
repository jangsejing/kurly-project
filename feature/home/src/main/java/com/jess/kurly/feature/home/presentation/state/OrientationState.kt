package com.jess.kurly.feature.home.presentation.state

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable

@Stable
sealed interface OrientationState {

    @Immutable
    data object Grid : OrientationState

    @Immutable
    data object Horizontal : OrientationState

    @Immutable
    data object Vertical : OrientationState

}