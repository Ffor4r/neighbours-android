package com.udevapp.neighbours.presentation.ui.fragments.main.home.place

import androidx.lifecycle.ViewModel
import com.udevapp.data.pojo.CalendarDateModel
import java.util.*
import kotlin.collections.ArrayList

class PlaceViewModel : ViewModel() {

    companion object {
        private const val dateFormat = "MMMM yyyy"
    }

    private val calendar = Calendar.getInstance(Locale.getDefault())
    val dates = ArrayList<CalendarDateModel>()

    fun getCurrentDay(): Int {
        return (calendar.clone() as Calendar).get(Calendar.DAY_OF_MONTH)
    }

    fun setUpCalendar() {
        val globalCalendar = calendar.clone() as Calendar
        val maxDaysInMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH)
        dates.clear()
        globalCalendar.set(Calendar.DAY_OF_MONTH, 1)
        while (dates.size < maxDaysInMonth) {
            dates.add(CalendarDateModel(globalCalendar.time))
            globalCalendar.add(Calendar.DAY_OF_MONTH, 1)
        }
    }

}