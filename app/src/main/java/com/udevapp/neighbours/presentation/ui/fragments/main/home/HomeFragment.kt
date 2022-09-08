package com.udevapp.neighbours.presentation.ui.fragments.main.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.udevapp.neighbours.databinding.FragmentHomeBinding
import com.udevapp.neighbours.presentation.ui.adapters.PlaceCollectionAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlin.random.Random

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

        binding.viewmodel = viewModel
        binding.pagerAdapter = PlaceCollectionAdapter(this)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupListeners()

    }

    private fun clickAddPlace() {
        binding.homeFloatingActionButton.setOnClickListener {
            val elem = Random.nextInt(0, 100)
            testPlaces.add(elem)
            binding.pagerAdapter?.setPlaces(testPlaces)
            binding.pagerAdapter?.notifyItemInserted(testPlaces.indexOf(elem))
        }
    }

    private fun setupListeners() {
        clickAddPlace()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}