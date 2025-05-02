package com.jess.kurly.data.model

import com.jess.kurly.domain.entity.SectionEntity
import com.jess.kurly.domain.entity.SectionsEntity
import com.jess.kurly.domain.type.SectionType

internal fun SectionsResponse.toEntity(): SectionsEntity {
    return SectionsEntity(
        data = data?.map {
            it.toEntity()
        }.orEmpty()
    )
}

internal fun SectionResponse.toEntity(): SectionEntity {
    return SectionEntity(
        id = id,
        type = SectionType.from(type),
        title = title,
        url = url,
    )
}