package com.udevapp.data.api

import com.udevapp.data.mappers.ApiErrorMapper
import okhttp3.ResponseBody

class ApiError(
    override val message: String?,
    val responseBody: ResponseBody?,
    val exception: Exception? = ApiErrorMapper().toError(responseBody)
) : Error() {

    data class Exception(
        val detail: String?,
        val title: String?,
        val type: String?,
        val violations: List<Violation>?
    )
    data class Violation(
        val propertyPath: String?,
        val title: String?
    )
}