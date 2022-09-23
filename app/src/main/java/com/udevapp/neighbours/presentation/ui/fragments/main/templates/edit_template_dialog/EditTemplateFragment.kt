package com.udevapp.neighbours.presentation.ui.fragments.main.templates.edit_template_dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.udevapp.data.api.schedule_template.ScheduleTemplateResponse
import com.udevapp.neighbours.R
import com.udevapp.neighbours.presentation.ui.fragments.main.templates.base.BaseTemplateDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class EditTemplateFragment(
    private val id: String,
    private val templateResponse: ScheduleTemplateResponse?
) : BaseTemplateDialogFragment() {

    companion object {
        const val TAG = "EditTemplateFragment"
    }

    override val viewModel by viewModels<EditTemplateViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = super.onCreateView(inflater, container, savedInstanceState)

        binding.title.text = resources.getString(R.string.fragment_edit_template_title)
        templateResponse?.let {
            daysAdapter.selectedDays = templateResponse.days
            templateResponse.performers.forEach { user ->
                performersAdapter.selectedPerformers.add(user.id)
            }
            viewModel.templateFormData.action = templateResponse.action
        }

        return view;
    }

    override fun actionDone() {
        viewModel.editTemplate(id)
    }
}