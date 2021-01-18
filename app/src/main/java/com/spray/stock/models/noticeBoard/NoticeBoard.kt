package com.spray.stock.models.noticeBoard

data class NoticeBoard (
    val id: Long,
    val noticeType: String,
    val topPosition: Boolean,
    val title: String,
    val content: String,
    val viewCount: Int,
    val lastModifiedDate: String,
    var expandable: Boolean = false
)