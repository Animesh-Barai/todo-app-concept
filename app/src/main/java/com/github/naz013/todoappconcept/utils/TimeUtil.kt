package com.github.naz013.todoappconcept.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date?.toUserReadableDate(): String {
    if (this == null) return ""
    return TimeUtil.userReadableDate.format(this)
}

fun Date?.toUserReadableTime(): String {
    if (this == null) return ""
    return TimeUtil.userReadableTime.format(this)
}

fun String?.toDate(): Date? {
    if (this == null) return null
    TimeUtil.serverTime.timeZone = TimeZone.getTimeZone("GMT")
    return try {
        TimeUtil.serverTime.parse(this)
    } catch (e: Exception) {
        null
    }
}

fun String?.toMillis(): Long {
    if (this == null) return 0
    return toDate()?.time ?: 0
}

fun Date?.nullOrServerTime(): String? {
    if (this == null) return null
    TimeUtil.serverTime.timeZone = TimeZone.getTimeZone("GMT")
    return TimeUtil.serverTime.format(this)
}

fun Date.toServerTime(): String {
    TimeUtil.serverTime.timeZone = TimeZone.getTimeZone("GMT")
    return TimeUtil.serverTime.format(this)
}

fun Date.toMonth(): String {
    return TimeUtil.MONTH.format(this)
}

object TimeUtil {
    val userReadableDate = SimpleDateFormat("MM.dd.YYYY", Locale.US)
    val userReadableTime = SimpleDateFormat("HH:mm", Locale.US)
    val serverTime = SimpleDateFormat("YYYY-MM-dd HH:mm", Locale.US)
    val MONTH = SimpleDateFormat("MMM", Locale.US)

    const val SECOND = 1000L
    const val MINUTE = SECOND * 60L
    const val HOUR = MINUTE * 60L
    const val DAY = HOUR * 24L
}