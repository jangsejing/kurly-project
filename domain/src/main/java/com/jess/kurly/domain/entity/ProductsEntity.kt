package com.jess.kurly.domain.entity

data class ProductsEntity(
    val data: List<ProductEntity>,
)

data class ProductEntity(
    val id: Long?,
    val name: String?,
    val image: String?,
    val originalPrice: Int?,
    val isSoldOut: Boolean?,
)