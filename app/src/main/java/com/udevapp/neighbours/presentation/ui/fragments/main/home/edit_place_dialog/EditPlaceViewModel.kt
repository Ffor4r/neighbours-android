package com.udevapp.neighbours.presentation.ui.fragments.main.home.edit_place_dialog

import androidx.lifecycle.viewModelScope
import com.udevapp.data.api.place.PlaceRequest
import com.udevapp.data.api.place.address.AddressRequest
import com.udevapp.domain.usecase.EditPlaceUseCase
import com.udevapp.neighbours.presentation.ui.fragments.main.home.base.BasePlaceMapDialogViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditPlaceViewModel @Inject constructor(private val editPlaceUseCase: EditPlaceUseCase) :
    BasePlaceMapDialogViewModel() {

    fun editPlace(id: String, addressRequest: AddressRequest, title: String = String()) {
        viewModelScope.launch {
            switchLoadingStatus()
            onSuccess(
                editPlaceUseCase.edit(
                    id,
                    PlaceRequest(
                        title = title,
                        address = addressRequest
                    )
                )
            ) {}
        }
    }
}