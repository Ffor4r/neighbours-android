package com.udevapp.neighbours.presentation.ui.fragments.main.profile.qrcode_dialog

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.viewModels
import com.udevapp.neighbours.R
import com.udevapp.neighbours.databinding.FragmentQrCodeBinding
import com.udevapp.neighbours.presentation.ui.fragments.main.base.FullScreenDialog
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class QrCodeFragment : FullScreenDialog() {

    companion object {
        const val TAG = "QrCodeFragment"
    }

    private var _binding: FragmentQrCodeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<QrCodeViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentQrCodeBinding.inflate(inflater, container, false)
        val view = binding.root

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




    }

}