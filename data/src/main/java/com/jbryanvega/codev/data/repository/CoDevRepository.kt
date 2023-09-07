package com.jbryanvega.codev.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import com.jbryanvega.codev.data.model.Applicant
import com.jbryanvega.codev.data.model.Applicants
import com.jbryanvega.codev.data.model.Job
import com.jbryanvega.codev.data.model.Jobs
import com.jbryanvega.codev.data.repository.remote.codev.CoDevServerRemoteDataSource
import com.jbryanvega.codev.data.request.ApplicantBody
import com.jbryanvega.codev.data.request.JobApplicantBody
import com.jbryanvega.codev.data.request.JobBody
import com.jbryanvega.codev.data.retrofit.RetrofitResponseCallback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoDevRepository
@Inject constructor(private val remoteDataSource: CoDevServerRemoteDataSource) : CoDevDataSource {

    override fun getApplicant(id: String): LiveData<Applicant> {
        val livedata = MutableLiveData<Applicant>()

        lateinit var successResponse: Applicant
        remoteDataSource.getApplicant(id, object: RetrofitResponseCallback {
            override fun onResponse(response: Response<JsonElement>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        val type = object : TypeToken<Applicant?>() {}.type
                        successResponse = Gson().fromJson(it, type)

                        livedata.value = successResponse

                        Timber.d("successResponse: $successResponse")
                    }
                }
            }

            override fun onFailure(element: JsonElement, throwable: Throwable?) {

            }

        })

        return livedata
    }

    override fun getAllApplicants() {
        remoteDataSource.getAllApplicants(object: RetrofitResponseCallback {
            override fun onResponse(response: Response<JsonElement>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        val type = object : TypeToken<List<Applicant>?>() {}.type
                        val successResponse: List<Applicant> = Gson().fromJson(it, type)

                        Timber.d("successResponse: $successResponse")
                    }
                }
            }

            override fun onFailure(element: JsonElement, throwable: Throwable?) {

            }

        })
    }

    override fun insertApplicant(body: ApplicantBody) {
        remoteDataSource.insertApplicant(body, object: RetrofitResponseCallback {
            override fun onResponse(response: Response<JsonElement>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        Timber.d("successResponse: $it")
                    }
                }
            }

            override fun onFailure(element: JsonElement, throwable: Throwable?) {

            }

        })
    }

    override fun updateApplicant(body: ApplicantBody) {
        remoteDataSource.updateApplicant(body, object: RetrofitResponseCallback {
            override fun onResponse(response: Response<JsonElement>) {

            }

            override fun onFailure(element: JsonElement, throwable: Throwable?) {

            }

        })
    }

    override fun deleteApplicant(id: String) {
        remoteDataSource.deleteApplicant(id, object: RetrofitResponseCallback {
            override fun onResponse(response: Response<JsonElement>) {

            }

            override fun onFailure(element: JsonElement, throwable: Throwable?) {

            }

        })
    }

    override fun getJob(keyword: String, jobIndustryType: Int) {
        remoteDataSource.getJob(keyword, jobIndustryType, object: RetrofitResponseCallback {
            override fun onResponse(response: Response<JsonElement>) {

            }

            override fun onFailure(element: JsonElement, throwable: Throwable?) {

            }

        })
    }

    override fun getJob(id: String) {
        remoteDataSource.getJob(id, object: RetrofitResponseCallback {
            override fun onResponse(response: Response<JsonElement>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        val type = object : TypeToken<Job?>() {}.type
                        val successResponse: Job = Gson().fromJson(it, type)

                        Timber.d("successResponse: $successResponse")
                    }
                }
            }

            override fun onFailure(element: JsonElement, throwable: Throwable?) {

            }

        })
    }

    override fun getAllJobs() {
        remoteDataSource.getAllJobs(object: RetrofitResponseCallback {
            override fun onResponse(response: Response<JsonElement>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        val type = object : TypeToken<List<Job>?>() {}.type
                        val successResponse: List<Job> = Gson().fromJson(it, type)

                        Timber.d("successResponse: $successResponse")
                    }
                }
            }

            override fun onFailure(element: JsonElement, throwable: Throwable?) {

            }

        })
    }

    override fun insertJob(body: JobBody) {
        remoteDataSource.insertJob(body, object: RetrofitResponseCallback {
            override fun onResponse(response: Response<JsonElement>) {

            }

            override fun onFailure(element: JsonElement, throwable: Throwable?) {

            }

        })
    }

    override fun updateJob(body: JobBody) {
        remoteDataSource.updateJob(body, object: RetrofitResponseCallback {
            override fun onResponse(response: Response<JsonElement>) {

            }

            override fun onFailure(element: JsonElement, throwable: Throwable?) {

            }

        })
    }

    override fun deleteJob(id: String) {
        remoteDataSource.deleteJob(id, object: RetrofitResponseCallback {
            override fun onResponse(response: Response<JsonElement>) {

            }

            override fun onFailure(element: JsonElement, throwable: Throwable?) {

            }

        })
    }

    override fun applyJob(id: String, body: JobApplicantBody) {
        remoteDataSource.applyJob(id, body, object: RetrofitResponseCallback {
            override fun onResponse(response: Response<JsonElement>) {

            }

            override fun onFailure(element: JsonElement, throwable: Throwable?) {

            }

        })
    }

    override fun getJobsApplied(id: String) {
        remoteDataSource.getJobsApplied(id, object: RetrofitResponseCallback {
            override fun onResponse(response: Response<JsonElement>) {

            }

            override fun onFailure(element: JsonElement, throwable: Throwable?) {

            }

        })
    }
}