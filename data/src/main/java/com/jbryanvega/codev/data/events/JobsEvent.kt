package com.jbryanvega.codev.data.events

import com.jbryanvega.codev.data.model.Job

data class JobsEvent(val jobs: List<Job>) {
}