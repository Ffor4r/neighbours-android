package com.udevapp.neighbours.presentation.ui.fragments.main.home.bottom_sheet_dialog

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.udevapp.data.api.place.PlaceResponse
import com.udevapp.data.db.entity.DefaultPlace
import com.udevapp.domain.usecase.DefaultPlaceUseCase
import com.udevapp.domain.usecase.GetCurrentUserUseCase
import com.udevapp.domain.usecase.GetPlaceUseCase
import com.udevapp.neighbours.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeBottomSheetViewModel @Inject constructor(
    private val getPlaceUseCase: GetPlaceUseCase,
    private val defaultPlaceUseCase: DefaultPlaceUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
) : BaseViewModel() {

    private val _places = MutableLiveData<List<PlaceResponse>>()
    val places: LiveData<List<PlaceResponse>> = _places

    private val _defaultPlaceIndex = MutableLiveData<Int>()
    val defaultPlaceIndex: LiveData<Int> = _defaultPlaceIndex

    private val _updateDefaultPlaceIndex = MutableLiveData<Int>()
    val updateDefaultPlaceIndex: LiveData<Int> = _updateDefaultPlaceIndex

    fun loadPlaces() {
        viewModelScope.launch {
            switchLoadingStatus()
            onSuccess(getPlaceUseCase.get()) {
                _places.value = it as List<PlaceResponse>
                viewModelScope.launch {
                    val defaultPlace =
                        defaultPlaceUseCase.getDefaultPlace(getCurrentUserUseCase.getUserToken()!!.id)
                            .getOrNull() as DefaultPlace?
                    defaultPlace?.let { dp -> _defaultPlaceIndex.value = dp.defaultIndex ?: 0 }
                }
            }
        }
    }

    fun updateDefaultPlace(position: Int) {
        viewModelScope.launch {
            switchLoadingStatus()
            defaultPlaceUseCase.setDefaultPlace(
                getCurrentUserUseCase.getUserToken()!!.id,
                position,
                places.value?.get(position)?.id!!
            )
            _updateDefaultPlaceIndex.value = position
        }
    }
}