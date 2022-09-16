package com.udevapp.neighbours.presentation.ui.fragments.main.home

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.internal.ToolbarUtils
import com.udevapp.neighbours.databinding.FragmentHomeBinding
import com.udevapp.neighbours.presentation.ui.fragments.main.home.bottom_sheet.HomeBottomSheetFragment
import com.udevapp.neighbours.presentation.ui.fragments.main.home.place.PlaceFragment
import dagger.hilt.android.AndroidEntryPoint

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
        viewModel.loadPlaces()
    }

    private fun setupObservers() {
        placesObserve()
        errorObserve()
    }

    private fun placesObserve() {
        viewModel.defaultPlaceIndex.observe(viewLifecycleOwner) {
            childFragmentManager.beginTransaction().apply {
                val fragment = PlaceFragment()
                fragment.arguments = Bundle().apply {
                    putString(PlaceFragment.TAG, viewModel.places.value?.get(it)?.id)
                }
                replace(binding.placePage.id, fragment)
                commit()
            }
        }
    }

    private fun errorObserve() {
        viewModel.error.observe(viewLifecycleOwner) {
        }
    }

    @SuppressLint("RestrictedApi")
    private fun clickAddressTitle() {
        ToolbarUtils.getTitleTextView(binding.topAppBar)?.setOnClickListener {
            clickToolbar()
        }
        ToolbarUtils.getSubtitleTextView(binding.topAppBar)?.setOnClickListener {
            clickToolbar()
        }
    }

    private fun clickToolbar() {
        val modalBottomSheet = HomeBottomSheetFragment(viewModel)
        modalBottomSheet.show(parentFragmentManager, HomeBottomSheetFragment.TAG)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}