package com.jess.kurly.domain.usecase

import com.jess.kurly.domain.entity.SectionsEntity
import com.jess.kurly.domain.repository.KurlyRepository
import javax.inject.Inject

class GetSectionsUserCase @Inject constructor(
    private val repository: KurlyRepository,
) {

    suspend operator fun invoke(
        page: Int,
    ): SectionsEntity {
        return repository.getSections(page)
    }
}
