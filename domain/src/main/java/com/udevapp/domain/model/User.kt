package com.udevapp.domain.model

data class User(
    var id: String? = null,

    var firstName: String? = null,

    var lastName: String? = null,

    var email: String? = null,

    var createdAt: String? = null,

    var password: String? = null,
)