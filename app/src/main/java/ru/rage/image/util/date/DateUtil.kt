package ru.rage.image.util.date

import java.util.*

object DateUtil{

    fun getCurrentCalendar() : Calendar = Calendar.getInstance()

    fun getCurrentDate() : Date = getCurrentCalendar().time

}