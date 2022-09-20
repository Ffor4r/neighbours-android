package com.udevapp.neighbours.presentation.ui.fragments.main.templates

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.udevapp.data.api.schedule_template.ScheduleTemplateResponse
import com.udevapp.domain.usecase.GetScheduleTemplateUseCase
import com.udevapp.neighbours.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TemplatesViewModel @Inject constructor(private val getScheduleTemplateUseCase: GetScheduleTemplateUseCase) :
    BaseViewModel() {

    private val _templates = MutableLiveData<List<ScheduleTemplateResponse>>()
    val templates: LiveData<List<ScheduleTemplateResponse>> = _templates

    fun loadTemplates() {
        viewModelScope.launch {
            switchLoadingStatus()
            onSuccess(getScheduleTemplateUseCase.get()) {
                _templates.value = it as List<ScheduleTemplateResponse>?
            }
        }
    }

}