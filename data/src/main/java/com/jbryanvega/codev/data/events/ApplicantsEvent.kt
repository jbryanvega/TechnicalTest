package com.jbryanvega.codev.data.events

import com.jbryanvega.codev.data.model.Applicant

data class ApplicantsEvent(val applicants: List<Applicant>) {
}