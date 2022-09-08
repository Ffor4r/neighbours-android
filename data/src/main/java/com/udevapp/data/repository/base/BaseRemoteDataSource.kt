package com.udevapp.data.repository.base

import com.udevapp.data.api.ApiError
import retrofit2.Response

abstract class BaseRemoteDataSource {

    protected fun <T>handleResponse(response: Response<T>) : Result<Any?> {
        return if (response.isSuccessful) {
            Result.success(response.body())
        } else {
            Result.failure(ApiError(message = response.message(), responseBody = response.errorBody()))
        }
    }

}