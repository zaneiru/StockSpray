package com.spray.stock.models

data class ApiPageResponse<T> (
    val content: ArrayList<T>,
    val totalPages: Int,
    val totalElements: Int,
    val last: Boolean
)
