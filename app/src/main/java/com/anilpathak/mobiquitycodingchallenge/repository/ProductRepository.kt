package com.anilpathak.mobiquitycodingchallenge.repository

import com.anilpathak.mobiquitycodingchallenge.retorfit.RetrofitModule
import kotlinx.coroutines.flow.flow

object ProductRepository {
    private val productService by lazy {
        RetrofitModule.provideProductService()
    }

    suspend fun getProducts() = flow {
        //creating a channel to emit values
        emit(DataState.Loading)
        try {
            val data = productService.getProducts()
            emit(DataState.Success(data))
        } catch (e: java.lang.Exception) {
            emit(DataState.Error(e))
        }
    }
}


sealed class DataState<out R> {
    data class Success<out T>(val data: T) : DataState<T>()
    data class Error(val exception: Exception) : DataState<Nothing>()
    object Loading : DataState<Nothing>()
}