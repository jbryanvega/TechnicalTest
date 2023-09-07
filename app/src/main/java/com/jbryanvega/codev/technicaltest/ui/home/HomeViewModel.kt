package com.jbryanvega.codev.technicaltest.ui.home

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jbryanvega.codev.data.model.Applicant
import com.jbryanvega.codev.data.repository.CoDevRepository
import com.jbryanvega.codev.data.request.ApplicantBody
import com.jbryanvega.codev.technicaltest.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel
@Inject constructor(application: Application, private val repository: CoDevRepository): BaseViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }
    val text: LiveData<String> = _text


    private val _applicant = MutableLiveData<Applicant>().apply {
        value = repository?.getApplicant("8856ad1b-c365-4cce-84df-97e13a65834c")?.value
    }
    val applicant: LiveData<Applicant> = _applicant

}