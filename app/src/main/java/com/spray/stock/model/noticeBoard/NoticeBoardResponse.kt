package com.spray.stock.model.noticeBoard

data class NoticeBoardResponse (
    val content: ArrayList<NoticeBoard>,
    val totalPages: Int,
    val totalElements: Int,
    val last: Boolean
)
