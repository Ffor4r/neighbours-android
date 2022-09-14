package com.udevapp.neighbours.presentation.ui.fragments.main.home

import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.MaterialToolbar

@BindingAdapter("app:place")
fun <T>place(toolbar: MaterialToolbar, places: LiveData<T>) {
    toolbar.title = places.value?.toString()
}

@BindingAdapter("app:place")
fun place(pager: ViewPager2, placeIndex: LiveData<Int>) {
    pager.setCurrentItem(placeIndex.value ?: 0, false)
}