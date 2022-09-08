package com.udevapp.domain.model

data class UserToken(
    var token: String?,

    val iat: String,

    val exp: String,

    val id: String,

    val email: String,

    val roles: List<String>
)