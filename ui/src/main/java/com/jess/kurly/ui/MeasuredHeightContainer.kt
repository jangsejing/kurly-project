package com.jess.kurly.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.SubcomposeLayout
import com.jess.kurly.ui.SlotId.Content
import com.jess.kurly.ui.SlotId.Measured

/**
 * @property Measured 높이를 측정하기 위한 슬록
 * @property Content 측정한 높이를 사용할 콘텐츠용 슬롯
 */
enum class SlotId {
    Measured,
    Content,
}

/**
 *  LazyRow의 높이를 아이템의 최대 높이로 고정하기 위한 컴포저블
 *
 *  @param measured 측정하고 싶은 높이를 갖는 Composable
 *  @param content 측정한 높이를 높이로 갖는 Composable
 */
@Composable
fun MeasuredHeightContainer(
    modifier: Modifier = Modifier,
    measured: @Composable () -> Unit,
    content: @Composable () -> Unit,
) {
    SubcomposeLayout(modifier = modifier) { constraints ->
        val wrappedConstraints = constraints.copy(minWidth = 0)
        val maxHeight = subcompose(SlotId.Measured, measured)
            .map { it.measure(wrappedConstraints) }
            .maxOf { it.height }
        val fixedHeightConstraints =
            wrappedConstraints.copy(minHeight = maxHeight, maxHeight = maxHeight)
        val placeable = subcompose(SlotId.Content, content)
            .map { it.measure(fixedHeightConstraints) }
        val layoutWidth = if (constraints.hasBoundedWidth && constraints.hasFixedWidth) {
            constraints.maxWidth
        } else {
            placeable.sumOf { it.width }.coerceIn(constraints.minWidth, constraints.maxWidth)
        }
        var xPos = 0
        layout(layoutWidth, maxHeight) {
            placeable.forEach { placeable ->
                placeable.placeRelative(xPos, 0)
                xPos += placeable.width
            }
        }
    }
}
