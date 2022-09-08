package com.udevapp.neighbours.presentation.ui.fragments.main.home

import androidx.databinding.BindingAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

@BindingAdapter("app:adapter")
fun adapter(pager: ViewPager2, fragmentStateAdapter: FragmentStateAdapter) {
    pager.adapter = fragmentStateAdapter
}

@BindingAdapter("app:pager")
fun pager(tabLayout: TabLayout, pager: ViewPager2) {
    TabLayoutMediator(tabLayout, pager) { tab, position ->
        tab.text = "OBJECT ${(position + 1)}"
    }.attach()
}