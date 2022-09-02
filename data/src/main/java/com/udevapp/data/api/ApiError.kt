package com.udevapp.data.api

data class ErrorModel(
    val detail: String?,
    val title: String?,
    val type: String?,
    val violations: List<Violation>?
) {
    data class Violation(
        val propertyPath: String?,
        val title: String?
    )
}