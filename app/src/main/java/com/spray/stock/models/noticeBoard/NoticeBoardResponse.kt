package com.spray.stock.models.noticeBoard

data class NoticeBoardResponse (
    val content: ArrayList<NoticeBoard>,
    val totalPages: Int,
    val totalElements: Int,
    val last: Boolean
)
