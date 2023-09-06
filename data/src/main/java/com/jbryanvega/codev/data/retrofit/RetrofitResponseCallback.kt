package com.jbryanvega.codev.data.retrofit

import com.google.gson.JsonElement
import retrofit2.Response

interface RetrofitResponseCallback {
    fun onResponse(response: Response<JsonElement>)
    fun onFailure(element: JsonElement, throwable: Throwable?)
}