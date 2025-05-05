package com.jess.kurly.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jess.kurly.domain.entity.ProductEntity
import com.jess.kurly.domain.type.OrientationType
import com.jess.kurly.domain.usecase.GetProductsUserCase
import com.jess.kurly.domain.usecase.GetSectionsUserCase
import com.jess.kurly.feature.home.presentation.state.HeartState
import com.jess.kurly.feature.home.presentation.state.HomeUiState
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
import kotlinx.coroutines.flow.update
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

    // 페이징 정보
    private var nextPage: Int = 1
    private var finishedPage: Boolean = false

    init {
        requestSections()
    }

    private fun requestSections(
        page: Int = 1,
    ) = viewModelScope.launch {

        if (finishedPage) {
            return@launch
        }

        _uiState.update { prev ->
            prev.copy(
                isRefreshing = true,
            )
        }

        kotlin.runCatching {
            val sections = getSections(page)
            val results = arrayListOf<SectionState>().apply {
                sections.data.forEachIndexed { index, entity ->
                    // 제목
                    add(SectionState.Title(entity.title))
                    val products = requestProducts(entity.id)
                    when (entity.type) {
                        OrientationType.GRID -> {
                            add(
                                SectionState.Grid(
                                    id = entity.id,
                                    products = products.take(6).toPersistentList(), // 6개 까지 
                                )
                            )
                        }

                        OrientationType.HORIZONTAL -> {
                            add(
                                SectionState.Horizontal(
                                    id = entity.id,
                                    products = products,
                                )
                            )
                        }

                        OrientationType.VERTICAL -> {
                            products.map { product ->
                                SectionState.Vertical(
                                    product.id,
                                    product
                                )
                            }.also {
                                addAll(it)
                            }
                        }

                        else -> Unit
                    }

                    if (index < sections.data.lastIndex) {
                        add(SectionState.Divider)
                    }
                }
            }

            _uiState.update { prev ->
                prev.copy(
                    sections = if (nextPage == 1) {
                        results.toPersistentList()
                    } else {
                        prev.sections.addAll(
                            results.toPersistentList(),
                        )
                    },
                )
            }

            // 페이지 정보 처리
            sections.nextPage?.let {
                nextPage = it
            } ?: run {
                finishedPage = true
            }
        }.onFailure {
            // Unable to resolve host "kurly.com": No address associated with hostname
            Timber.e(it)
        }

        _uiState.update { prev ->
            prev.copy(
                isRefreshing = false,
            )
        }
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
            val diff = (originalPrice - discountedPrice)
            val rate = (diff.toDouble() / originalPrice) * 100
            PriceState(
                discountRate = rate.toInt(),
                originalPrice = originalPrice,
                sellingPrice = discountedPrice,
            )
        } else {
            PriceState(
                sellingPrice = originalPrice,
            )
        }
    }

    fun onLoadMore() {
        Timber.d("onLoadMore")
        requestSections(nextPage)
    }

    fun onRefresh() {
        Timber.d("onRefresh")
        nextPage = 1
        finishedPage = false
        requestSections()
    }
}