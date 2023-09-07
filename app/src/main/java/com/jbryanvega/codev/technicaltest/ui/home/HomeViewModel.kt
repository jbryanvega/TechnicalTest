package com.jbryanvega.codev.technicaltest.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.jbryanvega.codev.data.events.ApplicantEvent
import com.jbryanvega.codev.data.events.ApplicantsEvent
import com.jbryanvega.codev.data.model.Applicant
import com.jbryanvega.codev.data.repository.CoDevRepository
import com.jbryanvega.codev.data.repository.remote.codev.CoDevRetrofitService
import com.jbryanvega.codev.data.request.ApplicantBody
import com.jbryanvega.codev.data.request.NewApplicantBody
import com.jbryanvega.codev.technicaltest.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode
import timber.log.Timber
import java.util.*
import javax.inject.Inject


@HiltViewModel
class HomeViewModel
@Inject constructor(application: Application,
                    private val coDevRetrofitService: CoDevRetrofitService,
                    private val repository: CoDevRepository): BaseViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text

    private val _applicant = MutableLiveData<Applicant>()
    val applicant: LiveData<Applicant> = _applicant

    private val _applicants = MutableLiveData<List<Applicant>>()
    val applicants: LiveData<List<Applicant>> = _applicants

    init {
        EventBus.getDefault().register(this)

        repository.getApplicant("8856ad1b-c365-4cce-84df-97e13a65834c")
        repository.getAllApplicants()
    }

    override fun onCleared() {
        super.onCleared()
        EventBus.getDefault().unregister(this)
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: ApplicantEvent?) {
        if (event != null) {
            _applicant.postValue(event.applicant)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun onMessageEvent(event: ApplicantsEvent?) {
        if (event != null) {
            _applicants.postValue(event.applicants)
        }
    }

    fun deleteApplicant(id: String) {
        repository.deleteApplicant(id)
    }

    fun updateApplicant(id: String) {
        val randomNumber = (1..100).random()
        repository.updateApplicant(ApplicantBody(id, "updated fullname $randomNumber", "updatedemail$randomNumber@gmail.com"))
    }

    fun addRandomApplicant() {
        val randomNumber = (1..100).random()
        val applicant = NewApplicantBody("added fullname $randomNumber", "addedemail$randomNumber@gmail.com")
        Timber.d(applicant.toString())
        repository.insertApplicant(applicant)
    }

    fun displayFirstApplicant(id: String) {
        repository.getApplicant(id)
    }
}