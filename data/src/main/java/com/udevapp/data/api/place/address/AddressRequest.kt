package com.udevapp.data.api.place.address

data class AddressRequest(

    val city: String? = String(),

    val street: String? = String(),

    val house: String? = String(),

    val apt: String? = String(),
)