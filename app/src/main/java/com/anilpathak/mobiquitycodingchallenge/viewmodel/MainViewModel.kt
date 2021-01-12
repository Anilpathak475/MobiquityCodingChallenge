package com.anilpathak.mobiquitycodingchallenge.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.anilpathak.mobiquitycodingchallenge.repository.DataState
import com.anilpathak.mobiquitycodingchallenge.repository.ProductRepository
import com.anilpathak.mobiquitycodingchallenge.retorfit.model.Category
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlin.coroutines.CoroutineContext

class MainViewModel() : ViewModel(), CoroutineScope {

    private var parentJob = SupervisorJob()

    override val coroutineContext: CoroutineContext = Dispatchers.Main + parentJob
    private val _products = MutableLiveData<DataState<List<Category>>>()
    val products = _products

    private val productRepository by lazy {
        ProductRepository
    }

    @ExperimentalCoroutinesApi
    fun getProducts() {
        productRepository.getProducts().onEach {
            _products.value = it
        }.launchIn(this)
    }

    override fun onCleared() {
        super.onCleared()
        coroutineContext.cancelChildren()
        coroutineContext.cancel()
    }
}