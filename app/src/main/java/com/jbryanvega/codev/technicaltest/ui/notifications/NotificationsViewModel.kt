package com.jbryanvega.codev.technicaltest.ui.notifications

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jbryanvega.codev.data.repository.CoDevRepository
import com.jbryanvega.codev.technicaltest.ui.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NotificationsViewModel
@Inject constructor(application: Application, private val repository: CoDevRepository): BaseViewModel(application) {

    private val _text = MutableLiveData<String>().apply {
        value = "This is notifications Fragment"
    }
    val text: LiveData<String> = _text
}