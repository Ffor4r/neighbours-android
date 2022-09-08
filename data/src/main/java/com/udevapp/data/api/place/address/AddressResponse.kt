package com.udevapp.data.api.place.address

import com.google.gson.annotations.SerializedName

data class AddressResponse(

    @SerializedName("id")
    var id: String,

    @SerializedName("city")
    val city: String,

    @SerializedName("street")
    val street: String,

    @SerializedName("house")
    val house: String,

    @SerializedName("apt")
    val apt: String,
)


