package com.jess.kurly.domain.repository

import com.jess.kurly.domain.entity.ProductsEntity
import com.jess.kurly.domain.entity.SectionsEntity

interface KurlyRepository {

    suspend fun getSections(
        page: Int,
    ): SectionsEntity

    suspend fun getProducts(
        sectionId: Int,
    ): ProductsEntity
}
