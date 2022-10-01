package com.udevapp.neighbours.presentation.ui.fragments.main.home.bottom_sheet_dialog

import android.Manifest
import android.annotation.SuppressLint
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.udevapp.data.api.place.PlaceResponse
import com.udevapp.neighbours.databinding.FragmentHomeBottomSheetBinding
import com.udevapp.neighbours.presentation.ui.fragments.main.home.add_place_dialog.AddPlaceFragment
import com.udevapp.neighbours.presentation.ui.fragments.main.home.base.BasePlaceMapDialogFragment
import com.udevapp.neighbours.presentation.ui.fragments.main.home.edit_place_dialog.EditPlaceFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeBottomSheetFragment : BottomSheetDialogFragment() {

    companion object {
        const val TAG = "HomeBottomSheetFragment"
    }

    private var _binding: FragmentHomeBottomSheetBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeBottomSheetViewModel>()

    private lateinit var adapter: AddressRecyclerViewAdapter

    private lateinit var onPositiveClickListener: OnDoneClickListener

    interface OnDoneClickListener {
        fun onClick(position: Int? = null)
    }

    fun setPositiveClickListener(listener: OnDoneClickListener) {
        onPositiveClickListener = listener
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBottomSheetBinding.inflate(inflater, container, false)
        val view = binding.root

        adapter = AddressRecyclerViewAdapter()
        adapter.setEditOnClickListener(OnEditClickListener())
        binding.addressList.adapter = adapter

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        loadData()
        setupListeners()
    }

    private fun loadData() {
        viewModel.loadPlaces()
    }

    private fun setupListeners() {
        clickButtonDone()
        clickButtonAdd()
    }

    private fun setupObservers() {
        updateDefaultPlace()
        placesObserve()
        defaultIndexObserve()
    }

    private fun placesObserve() {
        viewModel.places.observe(viewLifecycleOwner) {
            adapter.setPlaces(it)
        }
    }

    private fun updateDefaultPlace() {
        viewModel.updateDefaultPlaceIndex.observe(viewLifecycleOwner) {
            onPositiveClickListener.onClick()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun defaultIndexObserve() {
        viewModel.defaultPlaceIndex.observe(viewLifecycleOwner) {
            adapter.selectedPosition = it
            adapter.notifyDataSetChanged()
        }
    }

    private fun clickButtonAdd() {
        binding.addNewAddress.setOnClickListener {
            checkPermission {
                showAddPlaceDialog()
            }
        }
    }

    inner class OnEditClickListener : AddressRecyclerViewAdapter.OnItemClickListener {
        override fun onItemClick(position: Int, placeResponse: PlaceResponse) {
            checkPermission {
                showEditPlaceDialog(position, placeResponse)
            }
        }
    }

    private fun showEditPlaceDialog(position: Int, placeResponse: PlaceResponse) {
        val editPlaceFragment = EditPlaceFragment(placeResponse)
        editPlaceFragment.setPositiveClickListener(OnDialogsDoneClickListener())
        editPlaceFragment.show(parentFragmentManager, EditPlaceFragment.TAG)
        this.dismiss()
    }

    private fun showAddPlaceDialog() {
        val addPlaceFragment = AddPlaceFragment()
        addPlaceFragment.setPositiveClickListener(OnDialogsDoneClickListener())
        addPlaceFragment.show(parentFragmentManager, AddPlaceFragment.TAG)
        this.dismiss()
    }

    inner class OnDialogsDoneClickListener : BasePlaceMapDialogFragment.OnDoneClickListener {
        override fun onClick() {
            onPositiveClickListener.onClick()
        }
    }

    private fun clickButtonDone() {
        binding.actionChangeSelectedAddress.setOnClickListener {
            viewModel.updateDefaultPlace(adapter.selectedPosition)
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

    private fun <R> registerForActivityResult(onSuccess: () -> R): ActivityResultLauncher<Array<String>> {
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