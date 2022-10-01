package com.udevapp.neighbours.presentation.ui.fragments.main.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.internal.ToolbarUtils
import com.udevapp.neighbours.databinding.FragmentHomeBinding
import com.udevapp.neighbours.presentation.ui.fragments.main.home.bottom_sheet_dialog.HomeBottomSheetFragment
import com.udevapp.neighbours.presentation.ui.fragments.main.home.place.PlaceFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

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

    private fun setupListeners() {
        clickAddressTitle()
    }

    private fun loadData() {
        val qrPlaceId = requireActivity().intent.dataString.toString().split("//").last()
        try {
            UUID.fromString(qrPlaceId)
            viewModel.addMember(qrPlaceId)
        } catch (exception: Exception) {
            viewModel.loadPlace()
        }
        viewModel.updateNotificationUserToken()
    }

    private fun setupObservers() {
        placeObserve()
    }

    private fun placeObserve() {
        viewModel.place.observe(viewLifecycleOwner) {
            childFragmentManager.beginTransaction().apply {
                val fragment = PlaceFragment()
                fragment.arguments = Bundle().apply {
                    putString(PlaceFragment.TAG, viewModel.place.value?.id)
                }
                replace(binding.placePage.id, fragment)
                commit()
            }
        }
    }

    private fun clickAddressTitle() {
        binding.addressTitle.setOnClickListener{
            clickToolbar()
        }
    }

    private fun clickToolbar() {
        val modalBottomSheetFragment = HomeBottomSheetFragment()
        modalBottomSheetFragment.setPositiveClickListener(BottomSheetFragmentPositiveClickListener())
        modalBottomSheetFragment.show(parentFragmentManager, HomeBottomSheetFragment.TAG)
    }

    inner class BottomSheetFragmentPositiveClickListener :
        HomeBottomSheetFragment.OnDoneClickListener {
        override fun onClick(position: Int?) {
            viewModel.loadPlace()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}