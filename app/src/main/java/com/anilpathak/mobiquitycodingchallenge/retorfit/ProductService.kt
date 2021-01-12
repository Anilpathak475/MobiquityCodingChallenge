package com.anilpathak.mobiquitycodingchallenge.retorfit

import com.anilpathak.mobiquitycodingchallenge.retorfit.model.Category
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {
    @GET(".")
    suspend fun getProducts():List<Category>

    @GET("{imagePath}")
    suspend fun getProductImage(@Path("imagePath") imageName: String): String
}