package com.jbryanvega.codev.lib.retrofit

import com.google.gson.JsonElement
import retrofit2.Response

interface RetrofitResponseCallback {
    fun onResponse(response: Response<JsonElement>)
    fun onFailure(element: JsonElement, throwable: Throwable?)
}