package com.udevapp.neighbours.presentation.ui.fragments.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.udevapp.data.api.place.PlaceResponse
import com.udevapp.data.db.entity.DefaultPlace
import com.udevapp.domain.usecase.CreatePlaceUseCase
import com.udevapp.domain.usecase.DefaultPlaceUseCase
import com.udevapp.domain.usecase.GetCurrentUserUseCase
import com.udevapp.domain.usecase.GetPlaceUseCase
import com.udevapp.neighbours.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPlaceUseCase: GetPlaceUseCase,
    private val defaultPlaceUseCase: DefaultPlaceUseCase,
    private val createPlaceUseCase: CreatePlaceUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : BaseViewModel() {

    private val _places = MutableLiveData<List<PlaceResponse>>()
    val places: LiveData<List<PlaceResponse>> = _places

    private val _defaultPlace = MutableLiveData<PlaceResponse>()
    val defaultPlace: LiveData<PlaceResponse> = _defaultPlace

    private val _defaultPlaceIndex = MutableLiveData<Int>()
    val defaultPlaceIndex: LiveData<Int> = _defaultPlaceIndex

    fun createPlace() {
        viewModelScope.launch {
            switchLoadingStatus()

//            onSuccess(createPlaceUseCase.create(
//                PlaceRequest()
//            )) {
//                Log.i("AAA", it.toString())
//            }
//
//            onSuccess(
//                loginUserUseCase.login(
//                    Base64.getEncoder()
//                        .encodeToString("$email:$password".toByteArray())
//                )
//            ) { _loginState.value = it.toString() }

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