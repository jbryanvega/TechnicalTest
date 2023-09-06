package com.jbryanvega.codev.data.request

import com.google.gson.annotations.SerializedName

data class JobApplicantBody(@SerializedName("fullName") val fullName: String,
                            @SerializedName("emailAddress") val emailAddress: String)