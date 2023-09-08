package com.jbryanvega.codev.data.request

import com.google.gson.annotations.SerializedName

data class NewJobBody(@SerializedName("noOfOpenings") val noOfOpenings: Int,
                      @SerializedName("title") val title: String,
                      @SerializedName("description") val description: String,
                      @SerializedName("industry") val industry: Int)