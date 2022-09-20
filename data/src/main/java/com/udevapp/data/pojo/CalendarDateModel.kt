package com.udevapp.data.pojo

import android.icu.text.SimpleDateFormat
import java.util.*

data class CalendarDateModel(var date: Date, var isSelected: Boolean = false) {
    val calendarDay: String
        get() = SimpleDateFormat("EE", Locale.getDefault()).format(date)

    val calendarDate: String
        get() {
            val cal = Calendar.getInstance()
            cal.time = date
            return cal[Calendar.DAY_OF_MONTH].toString()
        }
}