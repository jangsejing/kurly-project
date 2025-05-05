package com.jess.kurly.data.repository

import com.jess.kurly.data.model.toEntity
import com.jess.kurly.data.remote.KurlyRemoteDataSource
import com.jess.kurly.domain.entity.ProductsEntity
import com.jess.kurly.domain.entity.SectionsEntity
import com.jess.kurly.domain.repository.KurlyRepository
import javax.inject.Inject

class KurlyRepositoryImpl @Inject constructor(
    private val remote: KurlyRemoteDataSource,
) : KurlyRepository {

    override suspend fun getSections(
        page: Int,
    ): SectionsEntity {
        return remote.getSections(page).toEntity()
    }

    override suspend fun getProducts(
        sectionId: Int,
    ): ProductsEntity {
        return remote.getProducts(sectionId).toEntity()
    }
}
