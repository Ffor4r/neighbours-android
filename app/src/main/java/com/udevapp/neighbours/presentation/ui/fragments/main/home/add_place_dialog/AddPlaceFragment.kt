package com.udevapp.neighbours.presentation.ui.fragments.main.home.add_place_dialog

import android.app.Dialog
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.udevapp.data.api.place.address.AddressRequest
import com.udevapp.neighbours.R
import com.udevapp.neighbours.databinding.FragmentAddPlaceBinding
import com.udevapp.neighbours.presentation.ui.fragments.main.base.MapCamera
import com.udevapp.neighbours.presentation.ui.fragments.main.home.HomeViewModel
import com.udevapp.neighbours.presentation.ui.fragments.main.home.base.BasePlaceMapDialogFragment
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.map.CameraListener
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.CameraUpdateReason
import com.yandex.mapkit.map.Map
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class AddPlaceFragment : BasePlaceMapDialogFragment() {

    companion object {
        const val TAG = "AddPlaceFragment"
    }

    override val viewModel by viewModels<AddPlaceViewModel>()


    override fun clickDone() {
        binding.done.setOnClickListener {
            val split = viewModel.addressText.value?.split(",")
            viewModel.createPlace(
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