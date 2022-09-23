package com.udevapp.neighbours.presentation.ui.fragments.main.home.place

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udevapp.data.api.schedule_template.ScheduleTemplateResponse
import com.udevapp.data.db.entity.DefaultPlace
import com.udevapp.data.pojo.CalendarDateModel
import com.udevapp.domain.usecase.GetScheduleTemplateUseCase
import com.udevapp.neighbours.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject
import kotlin.collections.ArrayList

@HiltViewModel
class PlaceViewModel @Inject constructor(private val getScheduleTemplateUseCase: GetScheduleTemplateUseCase) :
    BaseViewModel() {

    companion object {
        private const val dateFormat = "MMMM yyyy"
    }

    private val calendar = Calendar.getInstance(Locale.getDefault())
    val dates = ArrayList<CalendarDateModel>()

    private val _templates = MutableLiveData<List<ScheduleTemplateResponse>>()
    val templates: LiveData<List<ScheduleTemplateResponse>> = _templates

    private val _filteredTemplates = MutableLiveData<List<ScheduleTemplateResponse>>()
    val filteredTemplates: LiveData<List<ScheduleTemplateResponse>> = _filteredTemplates

    var defaultPlaceId: String? = null

    fun filterTemplates(day: Int) {
        _filteredTemplates.value = templates.value!!.filter { day in it.days }
    }

    fun getCurrentDay(): Int {
        return (calendar.clone() as Calendar).get(Calendar.DAY_OF_MONTH)
    }

    fun loadTemplates() {
        viewModelScope.launch {
            switchLoadingStatus()
            onSuccess(getScheduleTemplateUseCase.get(defaultPlaceId)) {
                _templates.value = it as List<ScheduleTemplateResponse>
            }
        }
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