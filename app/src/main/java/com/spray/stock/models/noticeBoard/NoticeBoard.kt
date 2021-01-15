package com.spray.stock.models.noticeBoard

import java.io.Serializable
import java.time.LocalDateTime

data class NoticeBoard (
    val id: Long,
    val noticeType: String,
    val topPosition: String,
    val title: String,
    val content: String,
    val viewCount: Int,
    val createdDate: LocalDateTime,
    val lastModifiedDate: LocalDateTime
) : Serializable