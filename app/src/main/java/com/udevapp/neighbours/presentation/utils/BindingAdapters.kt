package com.udevapp.neighbours.presentation.utils

import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.textfield.TextInputLayout
import com.udevapp.data.api.Violation

@BindingAdapter("app:loading")
fun loading(linearProgressIndicator: LinearProgressIndicator, loading: LiveData<Boolean>) {
    linearProgressIndicator.setVisibilityAfterHide(View.GONE)
    if (loading.value == true) linearProgressIndicator.show() else linearProgressIndicator.hide()
}

@BindingAdapter(value = ["app:error", "app:propertyPath"], requireAll = false)
fun error(textInputLayout: TextInputLayout, error: LiveData<Violation>, propertyPath: String = ""){
    val mappedError = error.value?.exception
    if (mappedError?.violations?.isNotEmpty() == true) {
        mappedError.violations!!.forEach {
            if (it.propertyPath == propertyPath) {
                textInputLayout.error = it.title
            }
        }
    } else {
        textInputLayout.error = error.value?.exception?.detail
    }
}

@BindingAdapter("app:errorListener")
fun errorListener(textInputLayout: TextInputLayout, enable: Boolean) {
    if (enable) {
        textInputLayout.editText?.doOnTextChanged {_, _, _, _ -> textInputLayout.error = null}
    }
}