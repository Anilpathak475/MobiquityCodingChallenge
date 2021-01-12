package com.anilpathak.mobiquitycodingchallenge.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.anilpathak.mobiquitycodingchallenge.R
import com.anilpathak.mobiquitycodingchallenge.adapters.ProductAdapter
import com.anilpathak.mobiquitycodingchallenge.repository.DataState
import com.anilpathak.mobiquitycodingchallenge.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private val adapter: ProductAdapter by lazy {
        ProductAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.products.observe(this, {
            when (it) {
                is DataState.Success -> {
                    adapter.submitList(it.data)
                    println(it.data)
                }
                is DataState.Error -> {
                    println(it.exception)
                }
                DataState.Loading -> {
                    println("Loading")
                }
            }
        })

        viewModel.getProducts()
        recyclerview_products.adapter = adapter

    }
}