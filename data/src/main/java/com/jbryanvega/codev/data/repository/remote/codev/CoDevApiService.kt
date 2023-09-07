package com.jbryanvega.codev.data.repository.remote.codev

import androidx.lifecycle.LiveData
import com.google.gson.JsonElement
import com.jbryanvega.codev.data.model.Applicant
import com.jbryanvega.codev.data.request.ApplicantBody
import com.jbryanvega.codev.data.request.JobApplicantBody
import com.jbryanvega.codev.data.request.JobBody
import com.jbryanvega.codev.data.request.NewApplicantBody
import com.jbryanvega.codev.lib.network.ApiResponse
import retrofit2.Call
import retrofit2.http.*

interface CoDevApiService {

    // region Applicant

    @Headers("Content-Type: application/json")
    @GET("api/Applicant/get/{id}")
    fun getApplicant(@Path("id") id: String): Call<JsonElement>

    @Headers("Content-Type: application/json")
    @GET("api/Applicant/get/{id}")
    fun getApplicantLD(@Path("id") id: String): LiveData<ApiResponse<Applicant>>


    @Headers("Content-Type: application/json")
    @GET("api/Applicant/getall")
    fun getAllApplicants(): Call<JsonElement>


    @Headers("Content-Type: application/json")
    @POST("api/Applicant/insert")
    fun insertApplicant(@Body body: NewApplicantBody): Call<JsonElement>


    @Headers("Content-Type: application/json")
    @PUT("api/Applicant/update")
    fun updateApplicant(@Body body: ApplicantBody): Call<JsonElement>


    @Headers("Content-Type: application/json")
    @DELETE("api/Applicant/delete")
    fun deleteApplicant(@Query("id") id: String): Call<JsonElement>

    // endregion Applicant




    // region Job

    @Headers("Content-Type: application/json")
    @GET("api/Job/filter")
    fun getJob(@Query("keyword") keyword: String,
               @Query("jobIndustryType") jobIndustryType: Int): Call<JsonElement>

    @Headers("Content-Type: application/json")
    @GET("api/Job/get/{id}")
    fun getJob(@Path("id") id: String): Call<JsonElement>


    @Headers("Content-Type: application/json")
    @GET("api/Job/getall")
    fun getAllJobs(): Call<JsonElement>

    @Headers("Content-Type: application/json")
    @POST("api/Job/insert")
    fun insertJob(@Body body: JobBody): Call<JsonElement>


    @Headers("Content-Type: application/json")
    @PUT("api/Job/update")
    fun updateJob(@Body body: JobBody): Call<JsonElement>


    @Headers("Content-Type: application/json")
    @DELETE("api/Job/delete")
    fun deleteJob(@Query("id") id: String): Call<JsonElement>

    // endregion Job


    // region JobApplicant

    @Headers("Content-Type: application/json")
    @POST("api/JobApplicant/applyjob/{jobId}")
    fun postJobApplicant(@Path("jobId") jobId: String,
                         @Body jobApplicantBody: JobApplicantBody): Call<JsonElement>


    @Headers("Content-Type: application/json")
    @GET("api/JobApplicant/getjobsapplied/{id}")
    fun getJobsApplied(@Path("id") id: String): Call<JsonElement>

    // endregion JobApplicant

}