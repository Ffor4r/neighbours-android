package com.udevapp.data.api.place

import com.google.gson.annotations.SerializedName
import com.udevapp.data.api.place.address.AddressResponse
import com.udevapp.data.api.user.UserResponse

data class PlaceResponse(

    @SerializedName("id")
    var id: String,

    @SerializedName("user")
    var user: UserResponse,

    @SerializedName("address")
    var address: AddressResponse,

    @SerializedName("members")
    var members: List<UserResponse>
)