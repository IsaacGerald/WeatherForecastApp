package com.senri.weatherforecastapp.common

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale


fun todayAtMidnight(): Date {
    val cal = Calendar.getInstance()
    cal.set(Calendar.HOUR_OF_DAY, 0)
    cal.set(Calendar.MINUTE, 0)
    cal.set(Calendar.SECOND, 0)
    cal.set(Calendar.MILLISECOND, 0)
    return cal.time
}

fun tomorrowAtMidnight(): Date {
    val cal = Calendar.getInstance()
    cal.add(Calendar.DAY_OF_YEAR, 1)
    cal.set(Calendar.HOUR_OF_DAY, 0)
    cal.set(Calendar.MINUTE, 0)
    cal.set(Calendar.SECOND, 0)
    cal.set(Calendar.MILLISECOND, 0)
    return cal.time
}

fun Long.checkDay(): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this * 1000  // convert seconds → millis

    val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)

    // Get today's date
    val todayCal = Calendar.getInstance()
    val isToday =
        todayCal.get(Calendar.YEAR) == calendar.get(Calendar.YEAR) &&
                todayCal.get(Calendar.DAY_OF_YEAR) == calendar.get(Calendar.DAY_OF_YEAR)

    return when {
        isToday -> "Today"
        dayOfWeek == Calendar.MONDAY -> "Monday"
        dayOfWeek == Calendar.TUESDAY -> "Tuesday"
        dayOfWeek == Calendar.WEDNESDAY -> "Wednesday"
        dayOfWeek == Calendar.THURSDAY -> "Thursday"
        dayOfWeek == Calendar.FRIDAY -> "Friday"
        dayOfWeek == Calendar.SATURDAY -> "Saturday"
        dayOfWeek == Calendar.SUNDAY -> "Sunday"
        else -> ""
    }
}

fun getCurrentDateFormatted(): String {
    val sdf = SimpleDateFormat("MMMM, dd", Locale.getDefault())
    return sdf.format(Date())
}

fun Date.toMonthDayFormat(): String {
    val sdf = SimpleDateFormat("MMMM, dd", Locale.getDefault())
    return sdf.format(this)
}

fun Date.toDateDayFormat(): String {
    val sdf = SimpleDateFormat("EEEE, d", Locale.getDefault())
    return sdf.format(this)
}