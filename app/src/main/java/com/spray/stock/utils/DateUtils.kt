package com.spray.stock.utils

import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter
import org.threeten.bp.temporal.ChronoUnit

fun String.diffTime(): String {
    val to = LocalDateTime.parse(this)
    val now = LocalDateTime.now()

    return when (val diff = LocalDateTime.from(to).until(now, ChronoUnit.MINUTES)) {
        in 0..59 -> diff.toString().toInt().toString().plus("분전")
        in 60..1439 -> (diff / 60).toInt().toString().plus("시간전")
        in 1440..43199 -> (diff / 60 / 24).toInt().toString().plus("일전")
        in 43200..525599 -> (diff / 60 / 24 / 30).toInt().toString().plus("개월전")
        else -> (diff / 60 / 24 / 30 / 12).toInt().toString().plus("년전")
    }
}

fun String.toRegularDate(): String {
    val date = LocalDateTime.parse(this)
    return LocalDateTime.parse(this).format(DateTimeFormatter.ofPattern("yyyyMMdd HH:mm:ss"))
}