package com.jess.kurly.data.remote

import com.jess.kurly.data.model.ProductsResponse
import com.jess.kurly.data.model.SectionsResponse
import retrofit2.http.GET

interface KurlyRemoteDatasource {

    @GET("sections")
    fun getSections(
        page: Int,
    ): SectionsResponse

    @GET("section/products")
    fun getProducts(
        sectionId: Int,
    ): ProductsResponse
}