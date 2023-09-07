package com.jbryanvega.codev.data.repository

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.reflect.TypeToken
import com.jbryanvega.codev.data.events.ApplicantEvent
import com.jbryanvega.codev.data.events.ApplicantsEvent
import com.jbryanvega.codev.data.events.JobEvent
import com.jbryanvega.codev.data.events.JobsEvent
import com.jbryanvega.codev.data.model.Applicant
import com.jbryanvega.codev.data.model.Job
import com.jbryanvega.codev.data.repository.remote.codev.CoDevServerRemoteDataSource
import com.jbryanvega.codev.data.request.ApplicantBody
import com.jbryanvega.codev.data.request.JobApplicantBody
import com.jbryanvega.codev.data.request.JobBody
import com.jbryanvega.codev.data.request.NewApplicantBody
import com.jbryanvega.codev.lib.retrofit.RetrofitResponseCallback
import org.greenrobot.eventbus.EventBus
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoDevRepository
@Inject constructor(private val remoteDataSource: CoDevServerRemoteDataSource) : CoDevDataSource {

    override fun getApplicant(id: String) {
        remoteDataSource.getApplicant(id, object: RetrofitResponseCallback {
            override fun onResponse(response: Response<JsonElement>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        val type = object : TypeToken<Applicant?>() {}.type
                        val successData: Applicant = Gson().fromJson(it, type)

                        Timber.d("successData: $successData")

                        EventBus.getDefault().post(ApplicantEvent(successData))
                    }
                }
            }

            override fun onFailure(element: JsonElement, throwable: Throwable?) {

            }
        })
    }


    override fun getAllApplicants() {
        remoteDataSource.getAllApplicants(object: RetrofitResponseCallback {
            override fun onResponse(response: Response<JsonElement>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        val type = object : TypeToken<List<Applicant>?>() {}.type
                        val successData: List<Applicant> = Gson().fromJson(it, type)

                        Timber.d("successData: $successData")

                        EventBus.getDefault().post(ApplicantsEvent(successData))
                    }
                }
            }

            override fun onFailure(element: JsonElement, throwable: Throwable?) {

            }

        })
    }

    override fun insertApplicant(body: NewApplicantBody) {
        remoteDataSource.insertApplicant(body, object: RetrofitResponseCallback {
            override fun onResponse(response: Response<JsonElement>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        Timber.d("successData: $it")
                    }
                } else {
                    Timber.d(response.message())
                }
                getAllApplicants()
            }

            override fun onFailure(element: JsonElement, throwable: Throwable?) {
                Timber.d(throwable)
                getAllApplicants()
            }

        })
    }

    override fun updateApplicant(body: ApplicantBody) {
        remoteDataSource.updateApplicant(body, object: RetrofitResponseCallback {
            override fun onResponse(response: Response<JsonElement>) {
                getAllApplicants()
            }

            override fun onFailure(element: JsonElement, throwable: Throwable?) {
                getAllApplicants()
            }

        })
    }

    override fun deleteApplicant(id: String) {
        remoteDataSource.deleteApplicant(id, object: RetrofitResponseCallback {
            override fun onResponse(response: Response<JsonElement>) {
                getAllApplicants()
            }

            override fun onFailure(element: JsonElement, throwable: Throwable?) {
                getAllApplicants()
            }

        })
    }

    override fun getJob(keyword: String, jobIndustryType: Int) {
        remoteDataSource.getJob(keyword, jobIndustryType, object: RetrofitResponseCallback {
            override fun onResponse(response: Response<JsonElement>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        val type = object : TypeToken<Job?>() {}.type
                        val successData: Job = Gson().fromJson(it, type)

                        Timber.d("successData: $successData")

                        EventBus.getDefault().post(JobEvent(successData))
                    }
                }
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
                        val successData: Job = Gson().fromJson(it, type)

                        Timber.d("successData: $successData")

                        EventBus.getDefault().post(JobEvent(successData))
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
                        val successData: List<Job> = Gson().fromJson(it, type)

                        Timber.d("successData: $successData")

                        EventBus.getDefault().post(JobsEvent(successData))
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
                getAllJobs()
            }

            override fun onFailure(element: JsonElement, throwable: Throwable?) {
                getAllJobs()
            }

        })
    }

    override fun updateJob(body: JobBody) {
        remoteDataSource.updateJob(body, object: RetrofitResponseCallback {
            override fun onResponse(response: Response<JsonElement>) {
                getAllJobs()
            }

            override fun onFailure(element: JsonElement, throwable: Throwable?) {
                getAllJobs()
            }

        })
    }

    override fun deleteJob(id: String) {
        remoteDataSource.deleteJob(id, object: RetrofitResponseCallback {
            override fun onResponse(response: Response<JsonElement>) {
                getAllJobs()
            }

            override fun onFailure(element: JsonElement, throwable: Throwable?) {
                getAllJobs()
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