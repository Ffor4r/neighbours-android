package com.udevapp.neighbours.presentation.ui.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.udevapp.neighbours.presentation.ui.fragments.main.home.place.PlaceFragment

class PlaceCollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private var places: List<Int> = listOf()

    override fun getItemCount(): Int = places.size

    override fun createFragment(position: Int): Fragment {
        val fragment = PlaceFragment()
        fragment.arguments = Bundle().apply {
            putInt("object", places[position])
        }
        return fragment
    }

    fun setPlaces(placeList: List<Int>) {
        places = placeList
    }
}