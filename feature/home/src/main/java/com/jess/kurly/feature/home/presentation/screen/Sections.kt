package com.jess.kurly.feature.home.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.jess.kurly.feature.home.presentation.state.OrientationState
import com.jess.kurly.feature.home.presentation.state.ProductState
import com.jess.kurly.feature.home.presentation.state.SectionState
import com.jess.kurly.ui.component.KurlyInfinityLazyColumn
import kotlinx.collections.immutable.PersistentList

@Composable
internal fun Sections(
    items: PersistentList<SectionState>,
    onLoadMore: () -> Unit,
    modifier: Modifier = Modifier,
    lazyListState: LazyListState = rememberLazyListState(),
) {
    KurlyInfinityLazyColumn(
        modifier = modifier.fillMaxSize(),
        state = lazyListState,
        onLoadMore = onLoadMore,
    ) {
        itemsIndexed(
            items = items,
            key = { index, item ->
                when (item) {
                    // Divider 는 hash 가 계속 같은 구조 이기 때문에 별도 key 처리
                    is SectionState.Divider -> item.javaClass.simpleName + "-$index"
                    else -> item.id ?: item.hashCode()
                }
            },
            contentType = { _, item ->
                item
            },
        ) { _, item ->
            when (item) {
                is SectionState.Title -> {
                    Text(
                        modifier = modifier.padding(8.dp),
                        fontWeight = FontWeight.ExtraBold,
                        text = item.title.orEmpty(),
                    )
                }

                is SectionState.Grid -> {
                    GridSection(
                        items = item.products,
                        onHeartClick = {

                        },
                    )
                }

                is SectionState.Horizontal -> {
                    HorizontalSection(
                        items = item.products,
                        onHeartClick = {

                        },
                    )
                }

                is SectionState.Vertical -> {
                    VerticalSection(
                        product = item.product,
                        onHeartClick = {

                        },
                    )
                }

                is SectionState.Divider -> {
                    HorizontalDivider(
                        modifier = Modifier.padding(
                            vertical = 8.dp,
                        ),
                        thickness = 0.2.dp,
                        color = Color.DarkGray,
                    )
                }
            }
        }
    }
}

@Composable
private fun GridSection(
    items: PersistentList<ProductState>,
    onHeartClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        modifier = modifier
            .fillMaxWidth()
            .height(620.dp),
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(horizontal = 4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        itemsIndexed(
            items = items,
            key = { _, item ->
                item.id ?: item.hashCode()
            },
        ) { _, item ->
            Product(
                modifier = Modifier.width(150.dp),
                imageModifier = Modifier
                    .size(150.dp, 200.dp),
                titleMaxLines = 2,
                product = item,
                orientation = OrientationState.Grid,
                onHeartClick = onHeartClick,
            )
        }
    }
}

@Composable
private fun HorizontalSection(
    items: PersistentList<ProductState>,
    onHeartClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyRow(
        modifier = modifier
            .fillMaxWidth()
            .height(310.dp),
        contentPadding = PaddingValues(horizontal = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(4.dp),
    ) {
        itemsIndexed(
            items = items,
            key = { _, item ->
                item.id ?: item.hashCode()
            },
        ) { _, item ->
            Product(
                modifier = Modifier.width(150.dp),
                imageModifier = Modifier
                    .size(150.dp, 200.dp),
                titleMaxLines = 2,
                product = item,
                orientation = OrientationState.Horizontal,
                onHeartClick = onHeartClick,
            )
        }
    }
}


@Composable
private fun VerticalSection(
    product: ProductState,
    onHeartClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Product(
        modifier = modifier,
        imageModifier = Modifier
            .fillMaxWidth()
            .aspectRatio(6f / 4f),
        titleMaxLines = 1,
        product = product,
        orientation = OrientationState.Vertical,
        onHeartClick = onHeartClick,
    )
}
