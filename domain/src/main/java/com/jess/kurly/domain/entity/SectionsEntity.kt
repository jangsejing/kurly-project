package com.jess.kurly.domain.entity

import com.jess.kurly.domain.type.SectionType

data class SectionsEntity(
    val data: List<SectionEntity>,
)

data class SectionEntity(
    val id: Long?,
    val type: SectionType?,
    val title: String?,
    val url: String?,
)