package com.udevapp.neighbours.presentation.ui.fragments.main.templates

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.udevapp.neighbours.databinding.FragmentTemplatesBinding
import com.udevapp.neighbours.presentation.ui.fragments.main.templates.add_template_dialog.AddTemplateFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TemplatesFragment : Fragment() {

    companion object {
        fun newInstance() = TemplatesFragment()
    }

    private var _binding: FragmentTemplatesBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<TemplatesViewModel>()
    private lateinit var adapter: TemplateAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTemplatesBinding.inflate(inflater, container, false)
        val view = binding.root

        adapter = TemplateAdapter(resources)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.templateView.adapter = adapter
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
        viewModel.loadTemplates()
    }

    private fun setupObservers() {
        placesObserve()
    }

    private fun setupListeners() {
        clickAddFab()
    }

    private fun clickAddFab() {
        binding.templatesFab.setOnClickListener {
            val dialog = AddTemplateFragment()
            dialog.setPositiveClickListener(object : AddTemplateFragment.OnDoneClickListener {
                override fun onClick() { viewModel.loadTemplates() }
            })
            dialog.show(parentFragmentManager, AddTemplateFragment.TAG)
        }
    }

    private fun placesObserve() {
        viewModel.templates.observe(viewLifecycleOwner) {
            adapter.setTemplates(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}