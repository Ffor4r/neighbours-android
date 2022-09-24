package com.udevapp.neighbours.presentation.ui.fragments.main.profile

import android.content.res.Configuration
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.viewModels
import androidx.preference.PreferenceFragmentCompat
import com.udevapp.neighbours.R
import com.udevapp.neighbours.databinding.FragmentProfileBinding
import com.udevapp.neighbours.presentation.extensions.activityNavController
import com.udevapp.neighbours.presentation.extensions.navigateSafely
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class ProfileFragment : Fragment() {

    companion object {
        fun newInstance() = ProfileFragment()
    }

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<ProfileViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        val view = binding.root

        val nightModeFlags =
            view.context.resources.configuration.uiMode and Configuration.UI_MODE_NIGHT_MASK

        binding.switchDarkMode.isChecked = nightModeFlags == Configuration.UI_MODE_NIGHT_YES
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
    }

    private fun setupListeners() {
        clickTopBarMenu()
        switchDarkMode()
    }

    private fun clickTopBarMenu() {
        binding.topAppBar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.logout -> {
                    viewModel.logout()
                    activityNavController().navigateSafely(R.id.action_global_signFlowFragment)
                    true
                }
                else -> false
            }
        }
    }

    private fun switchDarkMode() {
        binding.switchDarkMode.setOnCheckedChangeListener { _, b ->
            if (b) {
                viewModel.darkMode = AppCompatDelegate.MODE_NIGHT_YES
            } else {
                viewModel.darkMode = AppCompatDelegate.MODE_NIGHT_NO
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}