package com.udevapp.neighbours.presentation.utils

import android.view.View
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import com.google.android.material.progressindicator.LinearProgressIndicator

@BindingAdapter("app:loading")
fun loading(view: View, loading: Boolean) {
    if (view !is LinearProgressIndicator) {
        return
    }
    view.setVisibilityAfterHide(View.GONE)
    if (loading) view.show() else view.hide()
}