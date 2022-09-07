package com.udevapp.neighbours.presentation.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udevapp.data.api.ApiError

abstract class BaseViewModel : ViewModel() {

    private val _error = MutableLiveData<ApiError>()
    val error: LiveData<ApiError> = _error

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> = _loading

    protected fun <R, T> onSuccess(result: Result<T>, onSuccess: (T) -> R) {
        result.fold(onSuccess) { _error.value = it as ApiError }
        if (isLoading()) {
            switchLoadingStatus()
        }
    }

    protected fun switchLoadingStatus() {
        _loading.value = !loading.value!!
    }

    private fun isLoading(): Boolean {
        return _loading.value!!
    }

}