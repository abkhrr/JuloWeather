package com.juloweather.utils.date

import java.text.SimpleDateFormat
import java.util.*

object DateUtils {

    fun convertLongToTime(time: Long): String {
        val date   = Date(time * 1000)
        val format = SimpleDateFormat("dd/yyyy/MM", Locale.getDefault())
        return format.format(date)
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