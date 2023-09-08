package com.jbryanvega.codev.data.repository;

import androidx.lifecycle.LiveData
import com.jbryanvega.codev.data.model.Applicant
import com.jbryanvega.codev.data.request.*
import com.jbryanvega.codev.lib.network.ApiResponse
import retrofit2.http.Path

interface CoDevDataSource {

    fun getApplicant(id: String)
    fun getAllApplicants()
    fun insertApplicant(body: NewApplicantBody)
    fun updateApplicant(body:ApplicantBody)
    fun deleteApplicant(id: String)

    fun getJobs(keyword: String, jobIndustryType: Int)
    fun getJob(id: String)
    fun getAllJobs()
    fun insertJob(body: NewJobBody)
    fun updateJob(body:JobBody)
    fun deleteJob(id: String)

    fun applyJob(id: String, body:JobApplicantBody)
    fun getJobsApplied(id: String)
}
