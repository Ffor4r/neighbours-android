package com.udevapp.neighbours.presentation.ui.fragments.main.templates.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.udevapp.data.api.place.PlaceResponse
import com.udevapp.data.api.schedule_template.ScheduleTemplateRequest
import com.udevapp.data.api.schedule_template.ScheduleTemplateResponse
import com.udevapp.data.db.entity.DefaultPlace
import com.udevapp.domain.usecase.DefaultPlaceUseCase
import com.udevapp.domain.usecase.GetCurrentUserUseCase
import com.udevapp.domain.usecase.GetPlaceUseCase
import com.udevapp.neighbours.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

abstract class BaseTemplateDialogViewModel(
    private val defaultPlaceUseCase: DefaultPlaceUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val getPlaceUseCase: GetPlaceUseCase
) : BaseViewModel() {
    private val _defaultPlace = MutableLiveData<PlaceResponse>()
    val defaultPlace: LiveData<PlaceResponse> = _defaultPlace

    val templateFormData: ScheduleTemplateRequest = ScheduleTemplateRequest()

    protected val _template = MutableLiveData<ScheduleTemplateResponse>()
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
}