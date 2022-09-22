package com.udevapp.neighbours.presentation.ui.fragments.main.templates.add_template_dialog

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.MultiAutoCompleteTextView.CommaTokenizer
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.udevapp.data.api.user.UserResponse
import com.udevapp.neighbours.R
import com.udevapp.neighbours.databinding.FragmentAddTemplateBinding
import com.udevapp.neighbours.presentation.ui.fragments.main.home.bottom_sheet.AddressRecyclerViewAdapter
import com.udevapp.neighbours.presentation.ui.fragments.main.templates.TemplatesFragment
import com.udevapp.neighbours.presentation.ui.fragments.main.templates.TemplatesViewModel
import com.udevapp.neighbours.presentation.utils.DaysGenerator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddTemplateFragment() : DialogFragment() {

    companion object {
        const val TAG = "AddTemplateFragment"
    }

    private var _binding: FragmentAddTemplateBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<AddTemplateViewModel>()
    private lateinit var daysAdapter: TemplateSelectableDaysAdapter
    private lateinit var performersAdapter: TemplatePerformersAdapter

    private lateinit var onPositiveClickListener: OnDoneClickListener

    interface OnDoneClickListener {
        fun onClick()
    }

    fun setPositiveClickListener(listener: OnDoneClickListener) {onPositiveClickListener = listener}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddTemplateBinding.inflate(inflater, container, false)
        val view = binding.root

        daysAdapter = TemplateSelectableDaysAdapter(DaysGenerator().generate())
        performersAdapter = TemplatePerformersAdapter()

        binding.dayView.adapter = daysAdapter
        binding.performersView.adapter = performersAdapter

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
        cancelClickListener()
        doneClickListener()
    }

    private fun loadData() {
        viewModel.loadDefaultPlace()
    }

    private fun setupObservers() {
        placeObserve()
        templateObserve()
        errorObserve()
    }

    private fun placeObserve() {
        viewModel.defaultPlace.observe(viewLifecycleOwner) {
            performersAdapter.setPerformers(it.members)
        }
    }

    private fun templateObserve() {
        viewModel.template.observe(viewLifecycleOwner) {
            onPositiveClickListener.onClick()
            dismiss()
        }
    }

    private fun errorObserve() {
        viewModel.error.observe(viewLifecycleOwner) {
        }
    }

    private fun doneClickListener() {
        binding.done.setOnClickListener {
            viewModel.templateFormData.days = daysAdapter.selectedDays
            viewModel.templateFormData.performers = performersAdapter.selectedPerformers
            viewModel.createTemplate()
        }
    }

    private fun cancelClickListener() = binding.cancel.setOnClickListener { dismiss() }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}