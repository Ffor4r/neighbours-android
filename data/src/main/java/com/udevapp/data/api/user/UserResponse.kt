package com.udevapp.data.api.user

import com.google.gson.annotations.SerializedName

data class UserResponse  (
    @SerializedName("id")
    var id: String,

    @SerializedName("firstname")
    var firstName: String,

    @SerializedName("lastname")
    var lastName: String,

    @SerializedName("email")
    var email: String,

    @SerializedName("createdAt")
    var createdAt: String


) {
    override fun toString(): String {
        return "$firstName $lastName"
    }
}