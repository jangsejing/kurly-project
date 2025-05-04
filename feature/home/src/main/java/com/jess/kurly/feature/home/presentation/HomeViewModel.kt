package com.jess.kurly.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jess.kurly.domain.entity.ProductEntity
import com.jess.kurly.domain.type.OrientationType
import com.jess.kurly.domain.usecase.GetProductsUserCase
import com.jess.kurly.domain.usecase.GetSectionsUserCase
import com.jess.kurly.feature.home.presentation.state.HeartState
import com.jess.kurly.feature.home.presentation.state.HomeUiState
import com.jess.kurly.feature.home.presentation.state.OrientationState
import com.jess.kurly.feature.home.presentation.state.PriceState
import com.jess.kurly.feature.home.presentation.state.ProductState
import com.jess.kurly.feature.home.presentation.state.SectionState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.collections.immutable.PersistentList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
internal class HomeViewModel @Inject constructor(
    private val getSections: GetSectionsUserCase,
    private val getProducts: GetProductsUserCase,
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState.initial())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        requestSections()
    }

    fun requestSections(
        page: Int = 1,
    ) = viewModelScope.launch {
        kotlin.runCatching {
            val sections = getSections(page)
            sections.data.map { data ->
                val sectionId = data.id
                SectionState(
                    id = data.id ?: data.hashCode(),
                    title = data.title,
                    orientation = when (data.type) {
                        OrientationType.GRID -> OrientationState.Grid
                        OrientationType.HORIZONTAL -> OrientationState.Horizontal
                        else -> OrientationState.Vertical
                    },
                    products = requestProducts(sectionId),
                )
            }.also {
                Timber.d("$it")
            }
        }.onFailure {
            Timber.e(it)
        }
        // Unable to resolve host "kurly.com": No address associated with hostname
    }

    

    private suspend fun requestProducts(
        sectionId: Int?,
    ): PersistentList<ProductState> {
        if (sectionId == null) {
            return persistentListOf()
        }

        val products = getProducts(sectionId)
        return products.data.map { data ->
            ProductState(
                id = data.id ?: data.hashCode(),
                title = data.name,
                heart = HeartState.Off,
                image = data.image,
                price = createPriceState(data)
            )
        }.toPersistentList()
    }

    private fun createPriceState(
        data: ProductEntity,
    ): PriceState? {

        val originalPrice = data.originalPrice
        val discountedPrice = data.discountedPrice

        if (originalPrice == null) {
            return null
        }

        // 할인된 가격이 있을 경우
        return if (discountedPrice != null && originalPrice > discountedPrice) {
            val diff = originalPrice - discountedPrice
            val rate = (diff / originalPrice) * 100
            PriceState(
                discountRate = rate,
                originalPrice = originalPrice,
                sellingPrice = discountedPrice,
            )
        } else {
            PriceState(
                sellingPrice = originalPrice,
            )
        }
    }

    fun onRefresh() {
        requestSections()
    }
}