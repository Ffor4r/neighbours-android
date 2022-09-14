package com.udevapp.data.repository.base

import com.udevapp.data.api.Violation
import retrofit2.Response

abstract class BaseLocalDataSource {

    protected fun <T>handleResult(response: T) : Result<Any?> {
        return if (response != null) {
            Result.success(response)
        } else {
            Result.failure(Violation("Object is null", null, null))
        }
    }

}