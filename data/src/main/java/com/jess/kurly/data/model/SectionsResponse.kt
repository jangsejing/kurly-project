package com.jess.kurly.data.model

import com.google.gson.annotations.SerializedName

data class SectionsResponse(
    @SerializedName("data")
    val data: List<SectionResponse>?,
    @SerializedName("paging")
    val paging: SectionPaging?,
)

data class SectionResponse(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("type")
    val type: String?,
    @SerializedName("title")
    val title: String?,
    @SerializedName("url")
    val url: String?,
)

data class SectionPaging(
    @SerializedName("next_page")
    val nextPage: Int?,
)