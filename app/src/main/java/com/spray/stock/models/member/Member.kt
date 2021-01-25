package com.spray.stock.models.member

data class Member(
    val id: Long,
    val uuid: String,
    val nickName: String?,
    val profileImageUrl: String?
)
