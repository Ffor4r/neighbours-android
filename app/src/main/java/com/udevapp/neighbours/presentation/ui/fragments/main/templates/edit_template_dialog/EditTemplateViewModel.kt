package com.udevapp.neighbours.presentation.ui.fragments.main.templates.edit_template_dialog

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udevapp.data.api.schedule_template.ScheduleTemplateResponse
import com.udevapp.domain.usecase.*
import com.udevapp.neighbours.presentation.ui.fragments.main.templates.base.BaseTemplateDialogViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditTemplateViewModel @Inject constructor(
    defaultPlaceUseCase: DefaultPlaceUseCase,
    getPlaceUseCase: GetPlaceUseCase,
    getCurrentUserUseCase: GetCurrentUserUseCase,
    private val editScheduleTemplateUseCase: EditScheduleTemplateUseCase
) : BaseTemplateDialogViewModel(defaultPlaceUseCase, getCurrentUserUseCase, getPlaceUseCase) {

    fun editTemplate(id: String) {
        switchLoadingStatus()
        viewModelScope.launch {
            onSuccess(editScheduleTemplateUseCase.put(id, templateFormData)) {
                _template.value = it as ScheduleTemplateResponse
            }
        }
    }
}