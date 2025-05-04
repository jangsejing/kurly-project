package com.jess.kurly.feature.home.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.jess.kurly.feature.home.R
import com.jess.kurly.feature.home.presentation.state.HeartState
import com.jess.kurly.feature.home.presentation.state.PriceState
import com.jess.kurly.feature.home.presentation.state.ProductState
import com.jess.kurly.ui.component.KurlyAsyncImage
import com.jess.kurly.ui.theme.KurlyColor

@Composable
fun ProductItem(
    product: ProductState,
    onHeartClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier,
    ) {
        IconButton(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(16.dp),
            onClick = onHeartClick,
        ) {
            Icon(
                painter = if (product.heart == HeartState.On) {
                    painterResource(R.drawable.ic_btn_heart_on)
                } else {
                    painterResource(R.drawable.ic_btn_heart_off)
                },
                contentDescription = stringResource(R.string.home_heart_content_description)
            )
        }

        Column(
            modifier = Modifier,
        ) {

            KurlyAsyncImage(
                modifier = Modifier.fillMaxWidth(),
                url = product.image,
                contentDescription = stringResource(
                    R.string.home_image_content_description,
                ).format(
                    product.title
                ),
            )

            Text(
                modifier = Modifier,
                text = product.title.orEmpty(),
                overflow = TextOverflow.Ellipsis,
                maxLines = 2,
            )

            if (product.price != null) {
                PriceItem(
                    price = product.price,
                )
            }
        }
    }
}

@Composable
internal fun PriceItem(
    price: PriceState,
    modifier: Modifier = Modifier,
) {
    // 가격
    val discountRate = price.discountRate
    val originalPrice = price.originalPrice
    val sellingPrice = price.sellingPrice
    Row(
        modifier = modifier,
    ) {
        // 할인율
        if (discountRate != null) {
            Text(
                modifier = Modifier,
                text = stringResource(R.string.home_price_rate).format(discountRate),
                color = KurlyColor.DiscountRate,
            )
        }

        // 판매가
        Text(
            modifier = Modifier,
            text = stringResource(R.string.home_price_won).format(sellingPrice),
        )
    }

    // 원가
    if (originalPrice != null) {
        Text(
            modifier = Modifier,
            text = stringResource(R.string.home_price_won).format(originalPrice),
            style = TextStyle(textDecoration = TextDecoration.LineThrough),
        )
    }
}