package com.udevapp.neighbours.presentation.ui.adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.udevapp.data.api.place.PlaceResponse
import com.udevapp.neighbours.presentation.ui.fragments.main.home.place.PlaceFragment

class PlaceCollectionAdapter(fragment: Fragment) : FragmentStateAdapter(fragment) {

    private var places: List<PlaceResponse> = listOf()

    override fun getItemCount(): Int = places.size

    override fun createFragment(position: Int): Fragment {
        val fragment = PlaceFragment()
        fragment.arguments = Bundle().apply {
            putString("object", places[position].address.apt)
        }
        return fragment
    }

    fun setPlaces(placeList: List<PlaceResponse>) {
        places = placeList
    }
}