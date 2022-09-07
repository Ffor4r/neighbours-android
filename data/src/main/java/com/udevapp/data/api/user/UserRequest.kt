package com.udevapp.data.api.user

data class UserRequest(

    val email: String? = String(),

    val firstname: String? = String(),

    val lastname: String? = String(),

    val password: String? = String(),
)