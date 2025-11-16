package com.senri.weatherforecastapp.common

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import java.util.TimeZone

fun Date.atMidnight(): Date {
    val cal = Calendar.getInstance()
    cal.time = this
    cal.set(Calendar.HOUR_OF_DAY, 0)
    cal.set(Calendar.MINUTE, 0)
    cal.set(Calendar.SECOND, 0)
    cal.set(Calendar.MILLISECOND, 0)
    return cal.time
}

fun Long.toDate(): Date {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this * 1000  // convert seconds → millis

    // Normalize to midnight so same date = same key
    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)


    return calendar.time
}

fun Long.toHourOnly12Hour(): String {
    val date = Date(this * 1000)
    val sdf = SimpleDateFormat("h a", Locale.getDefault())
    sdf.timeZone = TimeZone.getDefault()
    return sdf.format(date)
}

fun Date.dateToString(format: String = "yyyy-MM-dd'T'HH:mm:ss", locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}

fun String.toDate(format: String = "yyyy-MM-dd'T'HH:mm:ss", locale: Locale = Locale.getDefault()): Date? {
    return try {
        SimpleDateFormat(format, locale).parse(this)
    } catch (e: Exception) {
        null
    }
}