package com.udevapp.neighbours.presentation.ui.fragments.main.home.place

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.udevapp.neighbours.databinding.FragmentPlaceBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlaceFragment : Fragment() {

    companion object {
        fun newInstance() = PlaceFragment()
        const val TAG = "PlaceFragment"
    }

    private var _binding: FragmentPlaceBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<PlaceViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlaceBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel.setUpCalendar()
        binding.calendarView.adapter = CalendarAdapter(viewModel.dates, viewModel.getCurrentDay() - 1)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.calendarView.layoutManager?.scrollToPosition(viewModel.getCurrentDay() - 1)

        arguments?.takeIf { it.containsKey(TAG) }
            ?.apply {
                val place = getString(TAG)

                binding.fragmentNumber.text = place
            }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}