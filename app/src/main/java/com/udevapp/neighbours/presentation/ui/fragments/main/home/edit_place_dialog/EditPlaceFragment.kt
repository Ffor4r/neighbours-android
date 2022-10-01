package com.udevapp.neighbours.presentation.ui.fragments.main.home.edit_place_dialog

import androidx.fragment.app.viewModels
import com.udevapp.data.api.place.PlaceResponse
import com.udevapp.data.api.place.address.AddressRequest
import com.udevapp.neighbours.presentation.ui.fragments.main.home.base.BasePlaceMapDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditPlaceFragment(private val placeResponse: PlaceResponse) : BasePlaceMapDialogFragment() {

    companion object {
        const val TAG = "EditPlaceFragment"
    }

    override val viewModel by viewModels<EditPlaceViewModel>()

    override fun clickDone() {
        binding.done.setOnClickListener {
            val split = viewModel.addressText.value?.split(",")
            val id: String = placeResponse.id
            viewModel.editPlace(
                id,
                AddressRequest(
                    city = viewModel.locationText.value?.split(",")?.first(),
                    street = split?.first()?.trim(),
                    house = split?.last()?.trim()
                )
            )
            onPositiveClickListener.onClick()
            dismiss()
        }
    }
}