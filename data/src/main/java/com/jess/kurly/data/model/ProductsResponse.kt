package com.jess.kurly.data.model

import com.google.gson.annotations.SerializedName

data class ProductsResponse(
    @SerializedName("data")
    val data: List<ProductResponse>?,
)

data class ProductResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("image")
    val image: String?,
    @SerializedName("originalPrice")
    val originalPrice: Int?,
    @SerializedName("discountedPrice")
    val discountedPrice: Int?,
    @SerializedName("isSoldOut")
    val isSoldOut: Boolean?,
)
