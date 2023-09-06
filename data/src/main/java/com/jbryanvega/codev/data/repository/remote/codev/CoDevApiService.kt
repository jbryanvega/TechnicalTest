package com.jbryanvega.codev.data.repository.remote.codev

import com.google.gson.JsonElement
import com.jbryanvega.codev.data.request.ApplicantBody
import com.jbryanvega.codev.data.request.JobApplicantBody
import com.jbryanvega.codev.data.request.JobBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path
import retrofit2.http.Query

interface CoDevApiService {

    // region Applicant

    @Headers("Content-Type: application/json")
    @GET("api/Applicant/get/{id}")
    fun getApplicant(@Path("id") id: String): Call<JsonElement>


    @Headers("Content-Type: application/json")
    @GET("api/Applicant/getall")
    fun getAllApplicants(): Call<JsonElement>


    @Headers("Content-Type: application/json")
    @POST("api/Applicant/insert")
    fun insertApplicant(@Body body: ApplicantBody): Call<JsonElement>


    @Headers("Content-Type: application/json")
    @PUT("api/Applicant/update")
    fun updateApplicant(@Body body: ApplicantBody): Call<JsonElement>


    @Headers("Content-Type: application/json")
    @DELETE("api/Applicant/delete")
    fun deleteApplicant(@Field("id") id: String): Call<JsonElement>

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
    fun deleteJob(@Field("id") id: String): Call<JsonElement>

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