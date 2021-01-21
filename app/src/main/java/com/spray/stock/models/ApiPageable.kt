package com.spray.stock.models

data class ApiPageable (
    val sort: ApiPageableSort,
    val offset: Long,
    val pageSize: Int,
    val pageNumber: Int,
    val unpaged: Boolean,
    val paged: Boolean

)