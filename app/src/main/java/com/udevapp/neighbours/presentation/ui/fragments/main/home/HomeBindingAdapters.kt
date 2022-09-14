package com.udevapp.neighbours.presentation.ui.fragments.main.home

import android.widget.Toolbar
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

@BindingAdapter("app:adapter")
fun adapter(pager: ViewPager2, fragmentStateAdapter: FragmentStateAdapter) {
    pager.adapter = fragmentStateAdapter
}

@BindingAdapter("app:userInputEnabled")
fun userInputEnabled(pager: ViewPager2, flag: Boolean) {
    pager.isUserInputEnabled = flag
}

@BindingAdapter("app:place")
fun <T>place(toolbar: MaterialToolbar, places: LiveData<T>) {
    toolbar.title = places.value?.toString()
}