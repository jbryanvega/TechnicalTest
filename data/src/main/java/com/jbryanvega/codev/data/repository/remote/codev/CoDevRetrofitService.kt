package com.jbryanvega.codev.data.repository.remote.codev

import com.jbryanvega.codev.data.retrofit.RetrofitService
import javax.inject.Inject

class CoDevRetrofitService @Inject constructor():
    RetrofitService<CoDevApiService>("https://codev-job-board-app.azurewebsites.net/", CoDevApiService::class.java) {

    fun service(): CoDevApiService {
        return service
    }
}