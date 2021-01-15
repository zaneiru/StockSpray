package com.spray.stock.models

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
class ServiceResponse<T> (
    @SerializedName("data")
    val data: T
)