package com.udevapp.data.api.login

import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("token")
    var token: String?,
)