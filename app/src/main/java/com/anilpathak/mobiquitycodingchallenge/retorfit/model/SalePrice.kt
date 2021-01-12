package com.anilpathak.mobiquitycodingchallenge.retorfit.model


import com.google.gson.annotations.SerializedName

data class SalePrice(
    @SerializedName("amount")
    val amount: String,
    @SerializedName("currency")
    val currency: String
)