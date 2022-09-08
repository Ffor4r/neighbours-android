package com.udevapp.neighbours.presentation.ui.fragments.main.home

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

@BindingAdapter("app:adapter")
fun adapter(pager: ViewPager2, fragmentStateAdapter: FragmentStateAdapter) {
    pager.adapter = fragmentStateAdapter
}

@BindingAdapter(value = ["app:pager", "app:pages"], requireAll = true)
fun <T>pager(tabLayout: TabLayout, pager: ViewPager2, pages: LiveData<List<T>>) {

    TabLayoutMediator(tabLayout, pager) { tab, position ->
        tab.text = pages.value?.get(position).toString()
    }.attach()
}