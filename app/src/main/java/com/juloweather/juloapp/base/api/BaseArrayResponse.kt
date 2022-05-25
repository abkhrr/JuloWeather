package com.juloweather.juloapp.base.api

import com.google.gson.annotations.SerializedName

data class BaseArrayResponse<T>(
    @SerializedName("message")
    val message: String,

    @SerializedName("status")
    val status: Int,

    @SerializedName("data")
    val data: List<T>
)