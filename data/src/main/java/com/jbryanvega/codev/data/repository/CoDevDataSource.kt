package com.jbryanvega.codev.data.repository;

import com.jbryanvega.codev.data.request.ApplicantBody;
import com.jbryanvega.codev.data.request.JobApplicantBody;
import com.jbryanvega.codev.data.request.JobBody;

interface CoDevDataSource {

    fun getApplicant(id: String)
    fun getAllApplicants()
    fun insertApplicant(body: ApplicantBody)
    fun updateApplicant(body:ApplicantBody)
    fun deleteApplicant(id: String)

    fun getJob(keyword: String, jobIndustryType: Int)
    fun getJob(id: String)
    fun getAllJobs()
    fun insertJob(body: JobBody)
    fun updateJob(body:JobBody)
    fun deleteJob(id: String)

    fun applyJob(id: String, body:JobApplicantBody)
    fun getJobsApplied(id: String)
}
