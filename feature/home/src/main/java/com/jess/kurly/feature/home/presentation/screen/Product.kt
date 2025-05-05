package com.jess.kurly.feature.home.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.jess.kurly.feature.home.R
import com.jess.kurly.feature.home.presentation.state.HeartState
import com.jess.kurly.feature.home.presentation.state.OrientationState
import com.jess.kurly.feature.home.presentation.state.PriceState
import com.jess.kurly.feature.home.presentation.state.ProductState
import com.jess.kurly.ui.component.KurlyAsyncImage
import com.jess.kurly.ui.theme.KurlyColor

@Composable
internal fun Product(
    product: ProductState,
    orientation: OrientationState,
    onHeartClick: () -> Unit,
    modifier: Modifier = Modifier,
    imageModifier: Modifier = Modifier,
    titleMaxLines: Int = Int.MAX_VALUE,
) {
    Box(
        modifier = modifier,
    ) {
        Column {
            KurlyAsyncImage(
                modifier = imageModifier,
                url = product.image,
                contentDescription = stringResource(
                    R.string.home_image_content_description,
                ).format(
                    product.title
                ),
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
            ) {
                // 상품명
                Text(
                    text = product.title.orEmpty(),
                    overflow = TextOverflow.Ellipsis,
                    maxLines = titleMaxLines,
                )

                // 가격
                if (product.price != null) {
                    Price(
                        price = product.price,
                        orientation = orientation,
                    )
                }
            }
        }

        IconButton(
            modifier = Modifier
                .align(Alignment.TopEnd),
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
    }
}

@Composable
internal fun Price(
    price: PriceState,
    orientation: OrientationState,
    modifier: Modifier = Modifier,
) {
    // 가격
    val discountRate = price.discountRate
    val originalPrice = price.originalPrice
    val sellingPrice = price.sellingPrice

    val discountRateText = stringResource(R.string.home_price_rate).format(discountRate)
    val originalPriceText = stringResource(R.string.home_price_won).format(originalPrice)
    val sellingPriceText = stringResource(R.string.home_price_won).format(sellingPrice)

    when (orientation) {
        is OrientationState.Vertical -> {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        top = 4.dp,
                    ),
                verticalAlignment = Alignment.CenterVertically,
            ) {
                // 할인율
                if (discountRate != null) {
                    Text(
                        text = discountRateText,
                        color = KurlyColor.DiscountRate,
                        fontWeight = FontWeight.Bold,
                    )

                    Spacer(
                        modifier = Modifier.width(4.dp),
                    )
                }

                // 판매가
                Text(
                    text = sellingPriceText,
                    fontWeight = FontWeight.Bold,
                )

                // 원가
                if (originalPrice != null) {
                    Text(
                        modifier = modifier.padding(
                            start = 4.dp,
                        ),
                        text = originalPriceText,
                        color = Color.Gray,
                        style = TextStyle(
                            textDecoration = TextDecoration.LineThrough,
                        ),
                    )
                }
            }
        }

        else -> {
            Column(
                modifier = modifier.padding(
                    top = 4.dp,
                ),
            ) {
                Row {
                    // 할인율
                    if (discountRate != null) {
                        Text(
                            text = discountRateText,
                            color = KurlyColor.DiscountRate,
                            fontWeight = FontWeight.Bold,
                        )

                        Spacer(
                            modifier = Modifier.width(4.dp),
                        )
                    }

                    // 판매가
                    Text(
                        text = sellingPriceText,
                        fontWeight = FontWeight.Bold,
                    )
                }

                // 원가
                if (originalPrice != null) {
                    Text(
                        text = originalPriceText,
                        color = Color.Gray,
                        style = TextStyle(textDecoration = TextDecoration.LineThrough),
                    )
                }
            }
        }
    }
}