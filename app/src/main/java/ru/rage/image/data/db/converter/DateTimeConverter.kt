package ru.rage.image.data.db.converter

import androidx.room.TypeConverter
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.concurrent.getOrSet

class DateTimeConverter {

    companion object {
        private val DATETIME_FORMATTER_THREAD_LOCAL = ThreadLocal<SimpleDateFormat>()
        private val DATETIME_FORMATTER: SimpleDateFormat
            get() = DATETIME_FORMATTER_THREAD_LOCAL.getOrSet { SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()) }
    }

    @TypeConverter
    fun fromStringToDate(date: String): Date {
        return try {
            DATETIME_FORMATTER.parse(date)
        } catch (e: ParseException) {
            Date(System.currentTimeMillis())
        }
    }

    @TypeConverter
    fun fromDateToString(date: Date): String {
        return DATETIME_FORMATTER.format(date)
    }

}