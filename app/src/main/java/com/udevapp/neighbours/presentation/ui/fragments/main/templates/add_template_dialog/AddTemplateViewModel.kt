package com.udevapp.neighbours.presentation.ui.fragments.main.templates.add_template_dialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udevapp.data.api.place.PlaceResponse
import com.udevapp.data.api.schedule_template.ScheduleTemplateRequest
import com.udevapp.data.api.schedule_template.ScheduleTemplateResponse
import com.udevapp.data.db.entity.DefaultPlace
import com.udevapp.domain.usecase.*
import com.udevapp.neighbours.presentation.base.BaseViewModel
import com.udevapp.neighbours.presentation.ui.fragments.main.templates.base.BaseTemplateDialogViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTemplateViewModel @Inject constructor(
    defaultPlaceUseCase: DefaultPlaceUseCase,
    getPlaceUseCase: GetPlaceUseCase,
    getCurrentUserUseCase: GetCurrentUserUseCase,
    private val createScheduleTemplateUseCase: CreateScheduleTemplateUseCase
) : BaseTemplateDialogViewModel(defaultPlaceUseCase, getCurrentUserUseCase, getPlaceUseCase) {

    fun createTemplate() {
        switchLoadingStatus()
        viewModelScope.launch {
            onSuccess(createScheduleTemplateUseCase.post(templateFormData)) {
                _template.value = it as ScheduleTemplateResponse
            }
        }
    }
}