package com.udevapp.neighbours.presentation.ui.fragments.main.home.place

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.udevapp.data.pojo.CalendarDateModel
import com.udevapp.neighbours.databinding.FragmentPlaceBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PlaceFragment : Fragment() {

    companion object {
        const val TAG = "PlaceFragment"
    }

    private var _binding: FragmentPlaceBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<PlaceViewModel>()
    private lateinit var daysAdapter: CalendarAdapter
    private lateinit var scheduleAdapter: ScheduleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlaceBinding.inflate(inflater, container, false)
        val view = binding.root

        viewModel.setUpCalendar()

        daysAdapter = CalendarAdapter(viewModel.dates, viewModel.getCurrentDay() - 1)
        daysAdapter.setOnDayClickListener(DayClickListener())
        binding.calendarView.adapter = daysAdapter

        scheduleAdapter = ScheduleAdapter(resources)
        binding.scheduleView.adapter = scheduleAdapter

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.calendarView.layoutManager?.scrollToPosition(viewModel.getCurrentDay() - 1)

        arguments?.takeIf { it.containsKey(TAG) }
            ?.apply {
                val place = getString(TAG)
                viewModel.defaultPlaceId = place
            }

        setupObservers()
        loadData()
    }

    private fun loadData() {
        viewModel.loadTemplates()
    }

    private fun setupObservers() {
        filteredTemplatesObserve()
        templatesObserve()
    }

    private fun templatesObserve() {
        viewModel.templates.observe(viewLifecycleOwner) {
            viewModel.filterTemplates(daysAdapter.getSelectedDay() - 1)
        }
    }

    private fun filteredTemplatesObserve() {
        viewModel.filteredTemplates.observe(viewLifecycleOwner) {
            scheduleAdapter.setTemplates(it)
        }
    }

    inner class DayClickListener : CalendarAdapter.OnDayClickListener {
        override fun onClick(position: Int, day: CalendarDateModel) {
            viewModel.filterTemplates(day.weekDate - 1)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}