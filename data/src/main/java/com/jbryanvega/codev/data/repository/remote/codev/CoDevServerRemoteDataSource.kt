package com.jbryanvega.codev.data.repository.remote.codev

import com.google.gson.JsonElement
import com.jbryanvega.codev.data.request.*
import com.jbryanvega.codev.lib.retrofit.ResponseJsonElement.Companion.API_FAILURE_JSON_ELEMENT
import com.jbryanvega.codev.lib.retrofit.RetrofitResponseCallback
import com.jbryanvega.codev.lib.thread.AppExecutors
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoDevServerRemoteDataSource
@Inject constructor(private val coDevRetrofitService: CoDevRetrofitService,
                    private val appExecutors: AppExecutors) {

    // region Applicant

    fun getApplicant(id: String, callback: RetrofitResponseCallback) {
        enqueueRestCall(coDevRetrofitService.service().getApplicant(id), callback)
    }

    fun getAllApplicants(callback: RetrofitResponseCallback) {
        enqueueRestCall(coDevRetrofitService.service().getAllApplicants(), callback)
    }

    fun insertApplicant(body: NewApplicantBody, callback: RetrofitResponseCallback) {
        enqueueRestCall(coDevRetrofitService.service().insertApplicant(body), callback)
    }

    fun updateApplicant(body: ApplicantBody, callback: RetrofitResponseCallback) {
        enqueueRestCall(coDevRetrofitService.service().updateApplicant(body), callback)
    }

    fun deleteApplicant(id: String, callback: RetrofitResponseCallback) {
        enqueueRestCall(coDevRetrofitService.service().deleteApplicant(id), callback)
    }

    // endregion Applicant



    // region Job

    fun getJobs(keyword: String, jobIndustryType: Int, callback: RetrofitResponseCallback) {
        enqueueRestCall(coDevRetrofitService.service().getJobs(keyword, jobIndustryType), callback)
    }

    fun getJob(id: String, callback: RetrofitResponseCallback) {
        enqueueRestCall(coDevRetrofitService.service().getJob(id), callback)
    }

    fun getAllJobs(callback: RetrofitResponseCallback) {
        enqueueRestCall(coDevRetrofitService.service().getAllJobs(), callback)
    }

    fun insertJob(body: NewJobBody, callback: RetrofitResponseCallback) {
        enqueueRestCall(coDevRetrofitService.service().insertJob(body), callback)
    }

    fun updateJob(body: JobBody, callback: RetrofitResponseCallback) {
        enqueueRestCall(coDevRetrofitService.service().updateJob(body), callback)
    }

    fun deleteJob(id: String, callback: RetrofitResponseCallback) {
        enqueueRestCall(coDevRetrofitService.service().deleteJob(id), callback)
    }

    // endregion Job


    // region JobApplicant

    fun applyJob(id: String, body: JobApplicantBody, callback: RetrofitResponseCallback) {
        enqueueRestCall(coDevRetrofitService.service().postJobApplicant(id, body), callback)
    }

    fun getJobsApplied(id: String, callback: RetrofitResponseCallback) {
        enqueueRestCall(coDevRetrofitService.service().getJobsApplied(id), callback)
    }


    // endregion JobApplicant



    fun enqueueRestCall(restCall: Call<JsonElement>, callback: RetrofitResponseCallback) {
        // run all network calls on separate thread
        appExecutors.networkIO().execute {
            restCall.enqueue(object : Callback<JsonElement> {
                override fun onResponse(call: Call<JsonElement>, response: Response<JsonElement>) {
                    // run callback on main thread
                    appExecutors.mainThread().execute {
                        callback.onResponse(response)
                    }
                }

                override fun onFailure(call: Call<JsonElement?>, throwable: Throwable) {
                    // run callback on main thread
                    appExecutors.mainThread().execute {
                        callback.onFailure(API_FAILURE_JSON_ELEMENT, throwable)
                    }
                }
            })
        }
    }

}