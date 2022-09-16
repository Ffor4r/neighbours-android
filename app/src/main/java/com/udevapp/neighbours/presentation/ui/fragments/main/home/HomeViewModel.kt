package com.udevapp.neighbours.presentation.ui.fragments.main.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.udevapp.data.api.place.PlaceRequest
import com.udevapp.data.api.place.PlaceResponse
import com.udevapp.data.api.place.address.AddressRequest
import com.udevapp.data.db.entity.DefaultPlace
import com.udevapp.domain.usecase.*
import com.udevapp.neighbours.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPlaceUseCase: GetPlaceUseCase,
    private val defaultPlaceUseCase: DefaultPlaceUseCase,
    private val createPlaceUseCase: CreatePlaceUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val editAddressUseCase: EditAddressUseCase
) : BaseViewModel() {

    private val _places = MutableLiveData<List<PlaceResponse>>()
    val places: LiveData<List<PlaceResponse>> = _places

    private val _defaultPlace = MutableLiveData<PlaceResponse>()
    val defaultPlace: LiveData<PlaceResponse> = _defaultPlace

    private val _defaultPlaceIndex = MutableLiveData<Int>()
    val defaultPlaceIndex: LiveData<Int> = _defaultPlaceIndex

    fun createPlace(addressRequest: AddressRequest, title: String = String()) {
        viewModelScope.launch {
            switchLoadingStatus()

            onSuccess(createPlaceUseCase.create(
                PlaceRequest(
                    user = getCurrentUserUseCase.getUserToken()?.id,
                    title = title,
                    address = addressRequest
                )
            )) {
                loadPlaces()
            }
        }
    }

    fun editPlace(id: String, addressRequest: AddressRequest, title: String = String()) {
        viewModelScope.launch {
            switchLoadingStatus()

            onSuccess(editAddressUseCase.edit(
                id,
                PlaceRequest(
                    title = title,
                    address = addressRequest
                )
            )) {
                loadPlaces()
            }
        }
    }

    fun loadPlaces() {
        viewModelScope.launch {
            switchLoadingStatus()
            onSuccess(getPlaceUseCase.get()) {
                viewModelScope.launch {
                    _places.value = it as List<PlaceResponse>?
                    val result =
                        defaultPlaceUseCase.getDefaultPlace(getCurrentUserUseCase.getUserToken()!!.id)
                    updateDefaultPlace((result.getOrNull() as DefaultPlace?)?.defaultIndex ?: 0)
                }
            }
        }
    }

    fun updateDefaultPlace(position: Int, force: Boolean) {
        updateDefaultPlace(position)
        if (force) {
            switchLoadingStatus()
            viewModelScope.launch {
                defaultPlaceUseCase.setDefaultPlace(
                    getCurrentUserUseCase.getUserToken()!!.id,
                    position
                )
                switchLoadingStatus()
            }
        }
    }

    private fun updateDefaultPlace(position: Int) {
        if (defaultPlaceIndex.value != position) {
            _defaultPlace.value = places.value?.get(position)
            _defaultPlaceIndex.value = position
        }
    }
}