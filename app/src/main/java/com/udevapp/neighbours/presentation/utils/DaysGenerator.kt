package com.udevapp.neighbours.presentation.utils

import com.udevapp.data.pojo.CalendarDateModel
import java.util.*

class DaysGenerator {
    private val calendar = Calendar.getInstance(Locale.getDefault())

    fun generate(): ArrayList<CalendarDateModel> {

        val dates = ArrayList<CalendarDateModel>()
        val globalCalendar = calendar.clone() as Calendar
        val maxDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_WEEK)
        dates.clear()
        globalCalendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY)
        while (dates.size < maxDaysInMonth) {
            dates.add(CalendarDateModel(globalCalendar.time))
            globalCalendar.add(Calendar.DAY_OF_WEEK, 1)
        }

        return dates
    }

}