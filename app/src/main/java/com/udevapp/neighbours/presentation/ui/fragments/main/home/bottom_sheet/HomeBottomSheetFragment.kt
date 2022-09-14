package com.udevapp.neighbours.presentation.ui.fragments.main.home.bottom_sheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.udevapp.neighbours.databinding.FragmentHomeBottomSheetBinding
import com.udevapp.neighbours.presentation.ui.fragments.main.home.HomeViewModel

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
            homeViewModel.getCurrentPlaceIndex()
        )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBottomSheetBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.addressList.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
    }

    private fun setupListeners() {
        clickButtonDone()
    }

    private fun clickButtonDone() {
        binding.actionChangeSelectedAddress.setOnClickListener {
            homeViewModel.changeCurrentPlace(adapter.getSelectedPosition())
            dismissAllowingStateLoss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}