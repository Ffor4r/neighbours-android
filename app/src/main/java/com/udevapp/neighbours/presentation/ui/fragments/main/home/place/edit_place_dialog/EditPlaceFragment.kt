package com.udevapp.neighbours.presentation.ui.fragments.main.home.place.edit_place_dialog

import com.udevapp.data.api.place.PlaceResponse
import com.udevapp.data.api.place.address.AddressRequest
import com.udevapp.neighbours.presentation.ui.fragments.main.home.HomeViewModel
import com.udevapp.neighbours.presentation.ui.fragments.main.home.place.add_place_dialog.AddPlaceFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditPlaceFragment(
    homeViewModel: HomeViewModel,
    private val placeResponse: PlaceResponse?,
    private val position: Int
) :
    AddPlaceFragment(homeViewModel) {
    override fun clickDone() {
        binding.done.setOnClickListener {
            val split = viewModel.addressText.value?.split(",")
            val id: String = placeResponse?.id ?: homeViewModel.places.value!![position].id
            homeViewModel.editPlace(
                id,
                AddressRequest(
                    city = viewModel.locationText.value?.split(",")?.first(),
                    street = split?.first()?.trim(),
                    house = split?.last()?.trim()
                )
            )
            dismiss()
        }
    }
}