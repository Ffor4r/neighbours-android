package com.udevapp.neighbours.presentation.ui.fragments.main.home.place.add_place_dialog

import android.app.Dialog
import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.udevapp.data.api.place.address.AddressRequest
import com.udevapp.neighbours.R
import com.udevapp.neighbours.databinding.FragmentAddPlaceBinding
import com.udevapp.neighbours.presentation.ui.fragments.main.home.HomeViewModel
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.map.CameraListener
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.CameraUpdateReason
import com.yandex.mapkit.map.Map
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
open class AddPlaceFragment(protected val homeViewModel: HomeViewModel) : DialogFragment() {

    companion object {
        const val TAG = "AddPlaceFragment"
    }

    private var _binding: FragmentAddPlaceBinding? = null
    protected val binding get() = _binding!!

    protected val viewModel by viewModels<AddPlaceViewModel>()

    private val addressCameraListener = AddressCameraListener()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.Theme_Neighbours_FullScreenDialog)

        MapKitFactory.initialize(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddPlaceBinding.inflate(inflater, container, false)
        val view = binding.root

        val nightModeFlags =
            view.context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK

        binding.mapview.map.isNightModeEnabled = nightModeFlags == Configuration.UI_MODE_NIGHT_YES

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupLocationObservers()
        setupListeners()

        viewModel.loadLastLocation()
        viewModel.loadUserLocation()
    }

    private fun setupMapCameraListener() {
        binding.mapview.map.addCameraListener(addressCameraListener)
    }

    private fun setupListeners() {
        setupMapCameraListener()
        clickFindPosition()
        clickFabNavigation()
        clickDone()
    }

    private fun setupLocationObservers() {
        viewModel.lastLocation.observe(viewLifecycleOwner) {
            it?.let { MapCamera.move(binding.mapview.map, it) }
        }
        viewModel.userLocation.observe(viewLifecycleOwner) {
            MapCamera.move(binding.mapview.map, it)
        }
        viewModel.currentPosition.observe(viewLifecycleOwner) {
            viewModel.searchAddress()
        }
        viewModel.addressText.observe(viewLifecycleOwner) {
            binding.addressText.text = it ?: resources.getString(R.string.searching)
        }
    }

    protected open fun clickDone() {
        binding.done.setOnClickListener {
            val split = viewModel.addressText.value?.split(",")
            homeViewModel.createPlace(
                AddressRequest(
                    city = viewModel.locationText.value?.split(",")?.first(),
                    street = split?.first()?.trim(),
                    house = split?.last()?.trim()
                )
            )
            dismiss()
        }
    }

    private fun clickFindPosition() {
        binding.fabUserLocation.setOnClickListener {
            viewModel.loadLastLocation()
            viewModel.loadUserLocation()
        }
    }

    private fun clickFabNavigation() {
        binding.fabNavigation.setOnClickListener { dismiss() }
    }

    override fun onStart() {
        super.onStart()
        val dialog: Dialog? = dialog
        if (dialog != null) {
            val width = ViewGroup.LayoutParams.MATCH_PARENT
            val height = ViewGroup.LayoutParams.MATCH_PARENT
            dialog.window?.setLayout(width, height)
            dialog.window?.setFlags(
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
            )
        }

        MapKitFactory.getInstance().onStart()
        binding.mapview.onStart()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        MapKitFactory.getInstance().onStop()
        binding.mapview.onStop()
        _binding = null
    }

    inner class AddressCameraListener : CameraListener {
        override fun onCameraPositionChanged(
            map: Map,
            cameraPosition: CameraPosition,
            cameraUpdateReason: CameraUpdateReason,
            finish: Boolean
        ) {
            if (finish) {
                viewModel.updateCurrentLocation(cameraPosition.target)
            } else {
                binding.addressText.text = resources.getString(R.string.searching)
            }
        }

    }
}