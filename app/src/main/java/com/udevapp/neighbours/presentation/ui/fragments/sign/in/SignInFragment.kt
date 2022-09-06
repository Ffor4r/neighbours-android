package com.udevapp.neighbours.presentation.ui.fragments.sign.`in`

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.udevapp.neighbours.R
import com.udevapp.neighbours.databinding.FragmentSignInBinding
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

        binding.viewmodel = viewModel

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupObservers()
        setupListeners()
    }

    private fun setupObservers() {
        loginSuccessObserve()
        errorObserve()
    }

    private fun setupListeners() {
        clickSignIn()
        clickSignUp()
    }

    private fun loginSuccessObserve() {
        viewModel.loginState.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Success Login", Toast.LENGTH_LONG).show()
        }
    }

    private fun errorObserve() {
        viewModel.error.observe(viewLifecycleOwner) {
            Toast.makeText(requireContext(), "Error Login", Toast.LENGTH_LONG).show()
        }
    }

    private fun clickSignUp() {
        binding.signUp.setOnClickListener{
            viewModel.logout()
            Toast.makeText(requireContext(), "Log Out", Toast.LENGTH_LONG).show()
//            findNavController().navigate(R.id.action_signInFragment_to_signUpFragment)
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