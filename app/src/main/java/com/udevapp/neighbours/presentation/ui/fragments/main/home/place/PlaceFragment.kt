package com.udevapp.neighbours.presentation.ui.fragments.main.home.place

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.udevapp.data.api.place.PlaceResponse
import com.udevapp.neighbours.R
import com.udevapp.neighbours.databinding.FragmentPlaceBinding

class PlaceFragment : Fragment() {

    companion object {
        fun newInstance() = PlaceFragment()
    }

    private var _binding: FragmentPlaceBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<PlaceViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPlaceBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.takeIf { it.containsKey("object") }
            ?.apply {
                val place = getString("object")

                binding.fragmentNumber.text = place
            }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}