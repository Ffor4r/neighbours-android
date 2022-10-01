package com.udevapp.neighbours.presentation.ui.fragments.main.home.base

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.*
import android.view.ViewGroup.MarginLayoutParams
import androidx.core.graphics.Insets
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updateLayoutParams
import androidx.core.view.updatePadding
import androidx.fragment.app.viewModels
import com.udevapp.neighbours.R
import com.udevapp.neighbours.databinding.FragmentAddPlaceBinding
import com.udevapp.neighbours.presentation.ui.fragments.main.base.FullScreenDialog
import com.udevapp.neighbours.presentation.ui.fragments.main.base.MapCamera
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.map.CameraListener
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.CameraUpdateReason
import com.yandex.mapkit.map.Map


abstract class BasePlaceMapDialogFragment : FullScreenDialog() {

    private var _binding: FragmentAddPlaceBinding? = null
    protected val binding get() = _binding!!

    protected open val viewModel by viewModels<BasePlaceMapDialogViewModel>()

    private val addressCameraListener = AddressCameraListener()

    protected lateinit var onPositiveClickListener: OnDoneClickListener

    interface OnDoneClickListener {
        fun onClick()
    }

    fun setPositiveClickListener(listener: OnDoneClickListener) {
        onPositiveClickListener = listener
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

        ViewCompat.setOnApplyWindowInsetsListener(binding.container) { v: View, windowInsets: WindowInsetsCompat ->
            val top = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars()).top
            val bottom = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars()).bottom

            if (top > 0 || bottom > 0) {
                v.updatePadding(
                    top = top,
                    bottom = bottom
                )
            }
            windowInsets
        }

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

    abstract fun clickDone()

    override fun onStart() {
        super.onStart()
        MapKitFactory.getInstance().onStart()
        binding.mapview.onStart()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        MapKitFactory.getInstance().onStop()
        binding.mapview.onStop()
        _binding = null
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