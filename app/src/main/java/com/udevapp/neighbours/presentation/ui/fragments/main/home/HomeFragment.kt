package com.udevapp.neighbours.presentation.ui.fragments.main.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.udevapp.neighbours.databinding.FragmentHomeBinding
import com.udevapp.neighbours.presentation.ui.adapters.PlaceCollectionAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()

    private var testPlaces: MutableList<Int> = mutableListOf()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewmodel = viewModel
        binding.pagerAdapter = PlaceCollectionAdapter(this)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()
        setupObservers()
        loadData()
    }

    private fun setupListeners() {
        clickAddPlace()
    }

    private fun loadData() {
        viewModel.loadPlaces()
    }

    private fun setupObservers() {
        placesObserve()
        errorObserve()
    }

    private fun placesObserve(){
        viewModel.places.observe(viewLifecycleOwner) {
            binding.pagerAdapter?.setPlaces(it)
            binding.pagerAdapter?.notifyDataSetChanged()
        }
    }

    private fun errorObserve() {
        viewModel.error.observe(viewLifecycleOwner) {
            Log.i("AAA", it.exception?.detail.toString())
        }
    }

    private fun clickAddPlace() {
        binding.homeFloatingActionButton.setOnClickListener {
//            val elem = Random.nextInt(0, 100)
//            testPlaces.add(elem)
//            binding.pagerAdapter?.setPlaces(testPlaces)
//            binding.pagerAdapter?.notifyItemInserted(testPlaces.indexOf(elem))
            viewModel.createPlace()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}