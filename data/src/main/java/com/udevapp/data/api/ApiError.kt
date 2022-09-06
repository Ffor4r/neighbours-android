package com.udevapp.data.api

import com.udevapp.data.api.mappers.ApiErrorMapper
import okhttp3.ResponseBody

class ApiError(
    override val message: String?,
    val responseBody: ResponseBody?
) : Error() {

    fun getMappedError() = ApiErrorMapper().toError(responseBody)

    data class Error(
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