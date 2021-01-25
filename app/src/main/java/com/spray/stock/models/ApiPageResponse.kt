package com.spray.stock.models

import com.google.gson.annotations.SerializedName

data class ApiPageResponse<T> (

    @SerializedName("content")
    val content: ArrayList<T>,

    @SerializedName("totalPages")
    val totalPages: Int,

    @SerializedName("totalElements")
    val totalElements: Int,

    @SerializedName("last")
    val last: Boolean
)
