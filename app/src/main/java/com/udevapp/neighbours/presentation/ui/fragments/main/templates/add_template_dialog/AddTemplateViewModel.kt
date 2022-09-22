package com.udevapp.neighbours.presentation.ui.fragments.main.templates.add_template_dialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udevapp.data.api.place.PlaceResponse
import com.udevapp.data.api.schedule_template.ScheduleTemplateRequest
import com.udevapp.data.api.schedule_template.ScheduleTemplateResponse
import com.udevapp.data.db.entity.DefaultPlace
import com.udevapp.domain.usecase.CreateScheduleTemplateUseCase
import com.udevapp.domain.usecase.DefaultPlaceUseCase
import com.udevapp.domain.usecase.GetCurrentUserUseCase
import com.udevapp.domain.usecase.GetPlaceUseCase
import com.udevapp.neighbours.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTemplateViewModel @Inject constructor(
    private val defaultPlaceUseCase: DefaultPlaceUseCase,
    private val getPlaceUseCase: GetPlaceUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val createScheduleTemplateUseCase: CreateScheduleTemplateUseCase
) : BaseViewModel() {

    private val _defaultPlace = MutableLiveData<PlaceResponse>()
    val defaultPlace: LiveData<PlaceResponse> = _defaultPlace

    val templateFormData: ScheduleTemplateRequest = ScheduleTemplateRequest()

    private val _template = MutableLiveData<ScheduleTemplateResponse>()
    val template: LiveData<ScheduleTemplateResponse> = _template

    fun loadDefaultPlace() {
        switchLoadingStatus()
        viewModelScope.launch {
            val result =
                defaultPlaceUseCase.getDefaultPlace(getCurrentUserUseCase.getUserToken()!!.id)
            viewModelScope.launch {
                (result.getOrNull() as DefaultPlace?)?.placeId?.let { placeId ->
                    onSuccess(getPlaceUseCase.get(placeId)) {
                        _defaultPlace.value = it as PlaceResponse
                        templateFormData.place = it.id
                    }
                }
            }
        }
    }

    fun createTemplate() {
        switchLoadingStatus()
        viewModelScope.launch {
            onSuccess(createScheduleTemplateUseCase.post(templateFormData)) {
                _template.value = it as ScheduleTemplateResponse
            }
        }
    }

}