package com.juloweather.utils.date

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    @JvmStatic
    fun toStringDate(date: Date) : String {
        val format = SimpleDateFormat("dd/MM/yyy", Locale.getDefault())
        return format.format(date)
    }

    @JvmStatic
    fun toStringTime(date: Date): String {
        val format = SimpleDateFormat("HH:mm", Locale.getDefault())
        return format.format(date)
    }

    @JvmStatic
    fun toLongDate(date: String) : Long {
        val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault())
        return format.parse(date).time
    }

    fun convertLongToTime(time: Long): String {
        val date   = Date(time)
        val format = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.getDefault())
        return format.format(date)
    }

    fun currentTimeToLong(): Long {
        return System.currentTimeMillis()
    }

    fun convertDateToLong(date: String): Long {
        val df = SimpleDateFormat("yyyy.MM.dd HH:mm", Locale.getDefault())
        return df.parse(date).time
    }

    fun pairOrderExpiredAndAlertedDate(expiryTime: Long, alertTime: Long): Pair<Long, Long> {
        val nowTime          = Date().time
        val timeRemExpiryMin = (expiryTime - nowTime).coerceAtLeast(0)
        val timeRemAlertMin  = (alertTime - nowTime).coerceAtLeast(0)

        return Pair(timeRemExpiryMin, timeRemAlertMin)
    }

    fun formatIntoMinAndSeconds(seconds: Long): String {
        return when {
            seconds >= 120 -> {
                "${seconds/60} mins"
            }
            seconds in 60..119 -> {
                "${seconds/60} min"
            }
            else -> {
                "$seconds s"
            }
        }
    }
}