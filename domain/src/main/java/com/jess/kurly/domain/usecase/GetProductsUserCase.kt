package com.jess.kurly.domain.usecase

import com.jess.kurly.domain.entity.ProductsEntity
import com.jess.kurly.domain.repository.KurlyRepository
import javax.inject.Inject

class GetProductsUserCase @Inject constructor(
    private val repository: KurlyRepository,
) {

    suspend operator fun invoke(
        sectionId: Int,
    ): ProductsEntity {
        return repository.getProducts(sectionId)
    }
}
