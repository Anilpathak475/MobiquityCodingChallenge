package com.anilpathak.mobiquitycodingchallenge.retorfit

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitModule {
    private fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor()
        logger.level = HttpLoggingInterceptor.Level.BODY
        return logger
    }

    private fun provideGson() = GsonBuilder().create()

    private fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        return OkHttpClient().newBuilder()
            .addInterceptor(loggingInterceptor).build()
    }


    private fun provideRetrofit(/*gson: Gson, okHttpClient: OkHttpClient*/): Retrofit {
        return Retrofit.Builder().baseUrl("http://mobcategories.s3")
            .client(provideOkHttpClient(provideLoggingInterceptor()))
            .addConverterFactory(GsonConverterFactory.create(provideGson()))
            .build()
    }


    fun provideProductService(/*retrofit: Retrofit*/) =
        provideRetrofit().create(ProductService::class.java)
}