package com.udevapp.neighbours.presentation.ui.fragments.main.home.add_place_dialog

import android.location.Address
import android.location.Geocoder
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.udevapp.data.api.place.PlaceRequest
import com.udevapp.data.api.place.address.AddressRequest
import com.udevapp.domain.usecase.CreatePlaceUseCase
import com.udevapp.domain.usecase.GetCurrentUserUseCase
import com.udevapp.neighbours.presentation.base.BaseViewModel
import com.udevapp.neighbours.presentation.ui.fragments.main.home.base.BasePlaceMapDialogViewModel
import com.yandex.mapkit.GeoObject
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.location.Location
import com.yandex.mapkit.location.LocationListener
import com.yandex.mapkit.location.LocationManagerUtils
import com.yandex.mapkit.location.LocationStatus
import com.yandex.mapkit.search.*
import com.yandex.runtime.Error
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddPlaceViewModel @Inject constructor(
    private val createPlaceUseCase: CreatePlaceUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
) :
    BasePlaceMapDialogViewModel() {

    fun createPlace(addressRequest: AddressRequest, title: String = String()) {
        viewModelScope.launch {
            switchLoadingStatus()
            onSuccess(
                createPlaceUseCase.create(
                    PlaceRequest(
                        user = getCurrentUserUseCase.getUserToken()?.id,
                        title = title,
                        address = addressRequest
                    )
                )
            ) {}
        }
    }

}