package com.udevapp.neighbours.presentation.ui.fragments.main.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udevapp.data.api.ApiError
import com.udevapp.data.api.place.PlaceRequest
import com.udevapp.data.api.place.PlaceResponse
import com.udevapp.domain.usecase.CreatePlaceUseCase
import com.udevapp.domain.usecase.GetCurrentUserUseCase
import com.udevapp.domain.usecase.GetPlaceUseCase
import com.udevapp.neighbours.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPlaceUseCase: GetPlaceUseCase,
    private val createPlaceUseCase: CreatePlaceUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase
) : BaseViewModel() {

    private val _places = MutableLiveData<List<PlaceResponse>>()
    val places: LiveData<List<PlaceResponse>> = _places

    fun createPlace() {
//        viewModelScope.launch {
//            switchLoadingStatus()
//
////            onSuccess(createPlaceUseCase.create(
////                PlaceRequest()
////            )) {
////                Log.i("AAA", it.toString())
////            }
//
////            onSuccess(
////                loginUserUseCase.login(
////                    Base64.getEncoder()
////                        .encodeToString("$email:$password".toByteArray())
////                )
////            ) { _loginState.value = it.toString() }
//
//        }
    }

    fun loadPlaces() {
        viewModelScope.launch {

            switchLoadingStatus()

            onSuccess(getPlaceUseCase.get()) {
                _places.value = it as List<PlaceResponse>
            }

        }
    }
}