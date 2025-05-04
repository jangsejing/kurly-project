package com.jess.kurly.domain.entity

import com.jess.kurly.domain.type.OrientationType

data class SectionsEntity(
    val data: List<SectionEntity>,
    val nextPage: Int?,
)

data class SectionEntity(
    val id: Int?,
    val title: String?,
    val type: OrientationType?,
    val url: String?,
)