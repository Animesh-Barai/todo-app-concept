package com.github.naz013.todoappconcept.utils

import com.github.naz013.todoappconcept.utils.TimeUtil.DAY
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*

fun Date?.toUserScreenDate(): String {
    if (this == null) return ""
    return when {
        this.diffInDays(Date()) == 0L -> {
            if (!this.after(Date())) {
                "Today"
            } else {
                "Tomorrow"
            }
        }
        else -> {
            TimeUtil.userDateFormat.format(this)
        }
    }
}

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
    return TimeUtil.monthFormat.format(this)
}

fun Date.diffInDays(date: Date): Long {
    val diff = this.time - date.time
    val days = diff / DAY
    Timber.d("diffInDays: $days, $this")
    return days
}

fun Date.zeroDate(): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this
    calendar.set(Calendar.HOUR, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 1)
    return calendar.time
}

object TimeUtil {
    val userReadableDate = SimpleDateFormat("MM.dd.YYYY", Locale.US)
    val userReadableTime = SimpleDateFormat("HH:mm", Locale.US)
    val serverTime = SimpleDateFormat("YYYY-MM-dd HH:mm", Locale.US)
    val monthFormat = SimpleDateFormat("MMM", Locale.US)
    val userDateFormat = SimpleDateFormat("MMM dd", Locale.US)

    const val SECOND = 1000L
    const val MINUTE = SECOND * 60L
    const val HOUR = MINUTE * 60L
    const val DAY = HOUR * 24L
}