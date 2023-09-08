package com.jbryanvega.codev.technicaltest.ui.dashboard

interface JobItemEventListener {
    fun onJobDelete(id: String)
    fun onJobUpdate(id: String)
}