package com.udevapp.neighbours.presentation.ui.fragments.main.templates

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.udevapp.data.api.place.PlaceResponse
import com.udevapp.data.api.schedule_template.ScheduleTemplateResponse
import com.udevapp.data.db.entity.DefaultPlace
import com.udevapp.domain.usecase.DefaultPlaceUseCase
import com.udevapp.domain.usecase.DeleteScheduleTemplateUseCase
import com.udevapp.domain.usecase.GetCurrentUserUseCase
import com.udevapp.domain.usecase.GetScheduleTemplateUseCase
import com.udevapp.neighbours.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TemplatesViewModel @Inject constructor(
    private val getScheduleTemplateUseCase: GetScheduleTemplateUseCase,
    private val defaultPlaceUseCase: DefaultPlaceUseCase,
    private val currentUserUseCase: GetCurrentUserUseCase,
    private val deleteScheduleTemplateUseCase: DeleteScheduleTemplateUseCase
) :
    BaseViewModel() {

    private val _templates = MutableLiveData<MutableList<ScheduleTemplateResponse>>()
    val templates: LiveData<MutableList<ScheduleTemplateResponse>> = _templates

    fun loadTemplates() {
        viewModelScope.launch {
            switchLoadingStatus()
            val result =
                defaultPlaceUseCase.getDefaultPlace(currentUserUseCase.getUserToken()!!.id)
            viewModelScope.launch {
                val placeId = (result.getOrNull() as DefaultPlace?)?.placeId
                if (placeId != null) {
                    onSuccess(getScheduleTemplateUseCase.get(placeId)) {
                        _templates.value = (it as List<ScheduleTemplateResponse>?)?.toMutableList()
                    }
                } else {
                    switchLoadingStatus()
                }
            }
        }
    }

    fun deleteTemplate(templateResponse: ScheduleTemplateResponse?) {
        viewModelScope.launch {
            onSuccess(deleteScheduleTemplateUseCase.delete(templateResponse?.id.toString())) {
                _templates.value?.remove(templateResponse)
            }
        }
    }

}