package com.example.domain.model

data class User(
    var id: String? = null,

    var firstName: String,

    var lastName: String,

    var email: String,

    var createdAt: String? = null,

    var password: String? = null
)