package com.anilpathak.mobiquitycodingchallenge.retorfit.model


import com.google.gson.annotations.SerializedName

data class Category(
    @SerializedName("description")
    val description: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("products")
    val products: List<Product>
)