package com.udevapp.data.api.user

data class UserRequest(

    var email: String? = null,

    var firstname: String? = null,

    var lastname: String? = null,

    var password: String? = null,

    var notificationToken: String? = null,
)