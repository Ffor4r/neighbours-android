package com.udevapp.neighbours.presentation.ui.fragments.main.templates

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.udevapp.data.api.schedule_template.ScheduleTemplateResponse
import com.udevapp.neighbours.R
import com.udevapp.neighbours.databinding.FragmentTemplatesBinding
import com.udevapp.neighbours.presentation.ui.fragments.main.templates.add_template_dialog.AddTemplateFragment
import com.udevapp.neighbours.presentation.ui.fragments.main.templates.base.BaseTemplateDialogFragment
import com.udevapp.neighbours.presentation.ui.fragments.main.templates.edit_template_dialog.EditTemplateFragment
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
        adapter.setEditOnClickListener(OnEditClickListener())
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
        hideAddFabOnScroll()
    }

    private fun clickAddFab() {
        binding.templatesFab.setOnClickListener {
            val dialog = AddTemplateFragment()
            dialog.setPositiveClickListener(PositiveClickListener())
            dialog.show(parentFragmentManager, AddTemplateFragment.TAG)
        }
    }

    private fun hideAddFabOnScroll() {
        binding.templateView.setOnScrollChangeListener { _, _, scrollY, _, oldScrollY ->
            if (scrollY > oldScrollY + 1 && binding.templatesFab.isShown) {
                binding.templatesFab.hide();
            }
            if (scrollY < oldScrollY - 1 && !binding.templatesFab.isShown) {
                binding.templatesFab.show();
            }
            if (scrollY == 0) {
                binding.templatesFab.show();
            }
        }
    }


    inner class OnEditClickListener : TemplateAdapter.OnItemClickListener {
        override fun onItemClick(position: Int, templateResponse: ScheduleTemplateResponse?) {
            val dialog = EditTemplateFragment(templateResponse?.id.toString(), templateResponse)
            dialog.setPositiveClickListener(PositiveClickListener())
            dialog.show(parentFragmentManager, EditTemplateFragment.TAG)
        }
        override fun onDeleteItemClick(position: Int, templateResponse: ScheduleTemplateResponse?) {
            MaterialAlertDialogBuilder(requireContext(),
                com.google.android.material.R.style.MaterialAlertDialog_Material3)
                .setMessage(resources.getString(R.string.delete_template_message))
                .setNegativeButton(resources.getString(R.string.cancel)) { dialog, _ ->
                    dialog.dismiss()
                }
                .setPositiveButton(resources.getString(R.string.done)) { dialog, _ ->
                    viewModel.deleteTemplate(templateResponse)
                    adapter.removeTemplate(position, templateResponse)
                    dialog.dismiss()
                }
                .show()
        }
    }

    inner class PositiveClickListener : BaseTemplateDialogFragment.OnDoneClickListener {
        override fun onClick() {
            viewModel.loadTemplates()
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