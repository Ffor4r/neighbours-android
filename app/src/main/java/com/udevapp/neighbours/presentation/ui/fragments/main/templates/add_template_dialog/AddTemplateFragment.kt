package com.udevapp.neighbours.presentation.ui.fragments.main.templates.add_template_dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.udevapp.neighbours.databinding.FragmentAddTemplateBinding
import com.udevapp.neighbours.presentation.ui.fragments.main.templates.base.BaseTemplateDialogFragment
import com.udevapp.neighbours.presentation.ui.fragments.main.templates.base.TemplatePerformersAdapter
import com.udevapp.neighbours.presentation.ui.fragments.main.templates.base.TemplateSelectableDaysAdapter
import com.udevapp.neighbours.presentation.utils.DaysGenerator
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class AddTemplateFragment : BaseTemplateDialogFragment() {

    companion object {
        const val TAG = "AddTemplateFragment"
    }

    override val viewModel by viewModels<AddTemplateViewModel>()

    override fun actionDone() {
        viewModel.createTemplate()
    }

}