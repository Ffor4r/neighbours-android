package com.udevapp.neighbours.presentation.ui.fragments.main.home.bottom_sheet

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.udevapp.data.api.place.PlaceResponse
import com.udevapp.neighbours.databinding.FragmentHomeBottomSheetBinding
import com.udevapp.neighbours.presentation.ui.fragments.main.home.HomeViewModel
import com.udevapp.neighbours.presentation.ui.fragments.main.home.place.add_place_dialog.AddPlaceFragment
import com.udevapp.neighbours.presentation.ui.fragments.main.home.place.edit_place_dialog.EditPlaceFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeBottomSheetFragment(private val homeViewModel: HomeViewModel) :
    BottomSheetDialogFragment() {

    companion object {
        const val TAG = "HomeBottomSheetFragment"
    }

    private var _binding: FragmentHomeBottomSheetBinding? = null
    private val binding get() = _binding!!

    private var adapter: AddressRecyclerViewAdapter =
        AddressRecyclerViewAdapter(
            homeViewModel.places,
            homeViewModel.defaultPlaceIndex.value ?: 0
        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBottomSheetBinding.inflate(inflater, container, false)
        val view = binding.root

        adapter.setEditOnClickListener(OnEditClickListener())
        binding.addressList.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
    }

    private fun setupListeners() {
        clickButtonDone()
        clickButtonAdd()
    }

    private fun clickButtonAdd() {
        binding.addNewAddress.setOnClickListener {
            checkPermission {
                showAddPlaceDialog()
            }
        }
    }

    inner class OnEditClickListener : AddressRecyclerViewAdapter.OnItemClickListener {
        override fun onItemClick(position: Int, placeResponse: PlaceResponse?) {
            checkPermission {
                showEditPlaceDialog(position, placeResponse)
            }
        }
    }

    private fun showEditPlaceDialog(position: Int, placeResponse: PlaceResponse?) {
        EditPlaceFragment(homeViewModel, placeResponse, position).show(
            parentFragmentManager,
            AddPlaceFragment.TAG
        )
        this.dismiss()
    }

    private fun showAddPlaceDialog() {
        AddPlaceFragment(homeViewModel).show(parentFragmentManager, AddPlaceFragment.TAG)
        this.dismiss()
    }

    private fun clickButtonDone() {
        binding.actionChangeSelectedAddress.setOnClickListener {
            homeViewModel.updateDefaultPlace(adapter.getSelectedPosition(), true)
            dismiss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun <R> checkPermission(onSuccess: () -> R) {
        when (PackageManager.PERMISSION_GRANTED) {
            ContextCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) -> {
                onSuccess()
            }
            else -> {
                registerForActivityResult(onSuccess).launch(
                    arrayOf(
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION
                    )
                )
            }
        }
    }

    private fun <R>registerForActivityResult(onSuccess: () -> R): ActivityResultLauncher<Array<String>> {
        return registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { permissions ->
            when {
                permissions.getOrDefault(
                    Manifest.permission.ACCESS_FINE_LOCATION, false
                ) -> {
                    onSuccess()
                }
                permissions.getOrDefault(
                    Manifest.permission.ACCESS_COARSE_LOCATION, false
                ) -> {
                    onSuccess()
                }
                else -> {
                }
            }
        }
    }
}