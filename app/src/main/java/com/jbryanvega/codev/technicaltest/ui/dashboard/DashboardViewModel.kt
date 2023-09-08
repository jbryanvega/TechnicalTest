package com.jbryanvega.codev.technicaltest.ui.dashboard

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jbryanvega.codev.data.events.JobEvent
import com.jbryanvega.codev.data.events.JobsEvent
import com.jbryanvega.codev.data.model.Job
import com.jbryanvega.codev.data.repository.CoDevRepository
import com.jbryanvega.codev.data.request.JobBody
import com.jbryanvega.codev.data.request.NewJobBody
import com.jbryanvega.codev.technicaltest.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel
@Inject constructor(application: Application,
                    private val repository: CoDevRepository): BaseViewModel(application) {

    private val _job = MutableLiveData<Job>()
    val job: LiveData<Job> = _job

    private val _jobs = MutableLiveData<List<Job>>()
    val jobs: LiveData<List<Job>> = _jobs

    init {
        EventBus.getDefault().register(this)

        repository.getAllJobs()
    }

    override fun onCleared() {
        super.onCleared()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: JobEvent?) {
        if (event != null) {
            _job.postValue(event.job)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: JobsEvent?) {
        if (event != null) {
            _jobs.postValue(event.jobs)
        }
    }

    fun deleteJob(id: String) {
        repository.deleteJob(id)
    }

    fun updateJob(id: String) {
        val randomNumber = (1..20).random()
        val randomIndustry = (0..8).random()
        repository.updateJob(JobBody(id, randomNumber, "updated title", "updated description", randomIndustry))
    }

    fun searchJob(keyword: String, industry: Int) {
        repository.getJobs(keyword, industry)
    }

    fun addRandomJob() {
        val randomNumber = (1..20).random()
        val randomIndustry = (0..8).random()
        val job = NewJobBody(randomNumber, "added title", "added description", randomIndustry)
        Timber.d(job.toString())
        repository.insertJob(job)
    }

    fun displayFirstJob(id: String) {
        repository.getJob(id)
    }

    fun clearJobSearch() {
        repository.getAllJobs()
    }
}