package com.udevapp.neighbours.presentation.ui.fragments.sign.`in`

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.udevapp.neighbours.R
import com.udevapp.neighbours.databinding.FragmentSignInBinding
import com.udevapp.neighbours.presentation.extensions.activityNavController
import com.udevapp.neighbours.presentation.extensions.navigateSafely
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {

    companion object {
        fun newInstance() = SignInFragment()
    }

    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<SignInViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (arguments?.get(R.id.action_signUpFragment_to_signInFragment.toString()) == true) {
            Snackbar.make(requireView(), "Success registration, please login!", Snackbar.LENGTH_LONG).show()
        }

        setupObservers()
        setupListeners()
    }

    private fun setupObservers() {
        loginSuccessObserve()
    }

    private fun setupListeners() {
        clickSignIn()
        clickSignUp()
    }

    private fun loginSuccessObserve() {
        viewModel.loginState.observe(viewLifecycleOwner) {
            activityNavController().navigateSafely(R.id.action_global_mainFlowFragment)
        }
    }

    private fun clickSignUp() {
        binding.signUp.setOnClickListener {
            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
        }
    }

    private fun clickSignIn() {
        binding.signIn.setOnClickListener {
            viewModel.login()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}