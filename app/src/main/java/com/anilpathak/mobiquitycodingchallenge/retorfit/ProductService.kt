package com.anilpathak.mobiquitycodingchallenge.retorfit

import com.anilpathak.mobiquitycodingchallenge.retorfit.model.Category
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductService {
    @GET("-website-eu-west-1.amazonaws.com/")
    fun getProducts():List<Category>

    @GET("website-eu-west-1.amazonaws.com{imagePath}")
    fun getProductImage(@Path("imagePath") imageName:String):List<Category>
}