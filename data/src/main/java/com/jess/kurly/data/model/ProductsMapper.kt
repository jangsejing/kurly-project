package com.jess.kurly.data.model

import com.jess.kurly.domain.entity.ProductEntity
import com.jess.kurly.domain.entity.ProductsEntity

internal fun ProductsResponse.toEntity(): ProductsEntity {
    return ProductsEntity(
        data = data?.map {
            it.toEntity()
        }.orEmpty(),
    )
}

internal fun ProductResponse.toEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        name = name,
        image = image,
        originalPrice = originalPrice,
        discountedPrice = discountedPrice,
        isSoldOut = isSoldOut,
    )
}
