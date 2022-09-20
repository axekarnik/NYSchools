package com.practice.nyschools.network

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient {
    companion object {
        val BASE_URL = "https://data.cityofnewyork.us/resource/"
        private const val TIME_OUT_DURATION = 60L

        private val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        private val httpClient = OkHttpClient.Builder().addInterceptor(interceptor)
            .connectTimeout(TIME_OUT_DURATION, TimeUnit.SECONDS)
            .build()

        fun getInstance() = Retrofit.Builder()
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create(GsonBuilder().create()))
            .baseUrl(BASE_URL)
            .build()
    }
}