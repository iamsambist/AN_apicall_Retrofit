package com.sunaa.apifetch.fetchdata.data.remote

import com.google.gson.annotations.SerializedName

data class JsonModel(
    @SerializedName("userId") val userId: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("body") val body: String
)
