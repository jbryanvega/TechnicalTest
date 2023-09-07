package com.jbryanvega.codev.lib.retrofit

import com.jbryanvega.codev.lib.network.LiveDataCallAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber

open class RetrofitService<out T>(baseUrl: String, clazz: Class<T>) {

    val service: T = Api.createRetrofitService(baseUrl, clazz)

    object Api {

        fun <T> createRetrofitService(baseUrl: String, service: Class<T>): T {
            Timber.d("baseUrl = $baseUrl")
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(service)
        }

        fun <T> createLiveDataService(baseUrl: String, service: Class<T>): T {
            Timber.d("baseUrl = $baseUrl")
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .build()
                .create(service)
        }
    }
}