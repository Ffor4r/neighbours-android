package com.udevapp.neighbours.presentation.ui.fragments.main.templates.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.udevapp.neighbours.databinding.FragmentAddTemplateBinding
import com.udevapp.neighbours.presentation.utils.DaysGenerator

abstract class BaseTemplateDialogFragment : DialogFragment() {

    protected open var _binding: FragmentAddTemplateBinding? = null
    protected val binding get() = _binding!!

    protected open val viewModel by viewModels<BaseTemplateDialogViewModel>()
    protected lateinit var daysAdapter: TemplateSelectableDaysAdapter
    protected lateinit var performersAdapter: TemplatePerformersAdapter

    private lateinit var onPositiveClickListener: OnDoneClickListener

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
            actionDone()
        }
    }


    protected abstract fun actionDone()

    private fun cancelClickListener() = binding.cancel.setOnClickListener { dismiss() }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}