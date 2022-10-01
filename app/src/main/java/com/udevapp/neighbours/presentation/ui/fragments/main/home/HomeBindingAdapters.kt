package com.udevapp.neighbours.presentation.ui.fragments.main.home

import android.view.View
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.appbar.MaterialToolbar

@BindingAdapter("app:place")
fun <T>place(toolbar: MaterialToolbar, places: LiveData<T>) {
    toolbar.title = places.value?.toString()
}

@BindingAdapter("app:place")
fun <T>place(textView: TextView, places: LiveData<T>) {
    if (places.value == null) {
        textView.visibility = View.GONE
        textView.text = null
    } else {
        textView.visibility = View.VISIBLE
        textView.text = places.value?.toString()
    }
}

@BindingAdapter("app:place")
fun place(pager: ViewPager2, placeIndex: LiveData<Int>) {
    pager.setCurrentItem(placeIndex.value ?: 0, false)
}