package com.udevapp.neighbours.presentation.ui.fragments.main.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.udevapp.data.api.place.PlaceResponse
import com.udevapp.domain.usecase.CreatePlaceUseCase
import com.udevapp.domain.usecase.DefaultPlaceUseCase
import com.udevapp.domain.usecase.GetCurrentUserUseCase
import com.udevapp.domain.usecase.GetPlaceUseCase
import com.udevapp.neighbours.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    private val _currentPlace = MutableLiveData<PlaceResponse>()
    val currentPlace: LiveData<PlaceResponse> = _currentPlace

    fun changeCurrentPlace(position: Int) {
        _currentPlace.value = places.value?.get(position)
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                defaultPlaceUseCase.setDefaultIndex(getCurrentUserUseCase.getUserToken()!!.id, position)
            }
        }
    }

    fun getCurrentPlaceIndex(): Int {
        defaultPlaceUseCase.getDefaultIndex(getCurrentUserUseCase.getUserToken()!!.id)
        TODO("make context call")
    }

    fun createPlace() {
        viewModelScope.launch {
            switchLoadingStatus()

//            onSuccess(createPlaceUseCase.create(
//                PlaceRequest()
//            )) {
//                Log.i("AAA", it.toString())
//            }

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
                _places.value = it as List<PlaceResponse>
                _currentPlace.value = places.value?.get(getCurrentPlaceIndex())
            }

        }
    }
}