package com.jess.kurly.data.model

import com.google.gson.annotations.SerializedName

data class SectionsResponse(
    @SerializedName("data")
    val data: List<SectionResponse>?,
)

data class SectionResponse(
    @SerializedName("id")
    val id: Long?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String?,
)

