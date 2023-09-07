package com.jbryanvega.codev.technicaltest.ui.dashboard

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jbryanvega.codev.data.repository.CoDevRepository
import com.jbryanvega.codev.technicaltest.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel
@Inject constructor(application: Application, private val repository: CoDevRepository): BaseViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is dashboard Fragment"
    }
    val text: LiveData<String> = _text
}