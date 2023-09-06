package com.jbryanvega.codev.data.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

open class RetrofitService<out T>(baseUrl: String, clazz: Class<T>) {

    val service: T = Api.createService(baseUrl, clazz)

    object Api {

        fun <T> createService(baseUrl: String, service: Class<T>): T {
            Timber.d("baseUrl = $baseUrl")
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(service)
        }
    }
}