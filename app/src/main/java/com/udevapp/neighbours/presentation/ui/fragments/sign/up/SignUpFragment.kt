package com.udevapp.neighbours.presentation.ui.fragments.sign.up

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.udevapp.neighbours.R
import com.udevapp.neighbours.databinding.FragmentSignUpBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment() {

    companion object {
        fun newInstance() = SignUpFragment()
    }

    private var _binding: FragmentSignUpBinding? = null
    private val binding get() = _binding!!

    val viewModel by viewModels<SignUpViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSignUpBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
        setupObservers()
    }

    private fun setupObservers() {
        userObserve()
    }

    private fun setupListeners() {
        clickSignIn()
        clickSignUp()
    }

    private fun userObserve() {
        viewModel.user.observe(viewLifecycleOwner) {
            findNavController().navigate(
                R.id.action_signUpFragment_to_signInFragment,
                bundleOf(
                    R.id.action_signUpFragment_to_signInFragment.toString() to true
                )
            )
        }
    }

    private fun clickSignUp() {
        binding.signUp.setOnClickListener {
            viewModel.createUser()
        }
    }

    private fun clickSignIn() {
        binding.signIn.setOnClickListener {
            findNavController().navigate(R.id.action_signUpFragment_to_signInFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}