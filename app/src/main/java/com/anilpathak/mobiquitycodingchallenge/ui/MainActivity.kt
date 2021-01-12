package com.anilpathak.mobiquitycodingchallenge.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.anilpathak.mobiquitycodingchallenge.R
import com.anilpathak.mobiquitycodingchallenge.adapters.ProductAdapter
import com.anilpathak.mobiquitycodingchallenge.repository.DataState
import com.anilpathak.mobiquitycodingchallenge.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    val viewModel by viewModels<MainViewModel>()
    val adapter: ProductAdapter by lazy {
        ProductAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel.products.observe(this, androidx.lifecycle.Observer {
            when (it) {
                is DataState.Success -> {
                    adapter.data = it.data
                }
                is DataState.Error -> {
                }
                DataState.Loading -> {

                }
            }
        })

        viewModel.getProducts()
        recyclerview_products.adapter = adapter

    }
}