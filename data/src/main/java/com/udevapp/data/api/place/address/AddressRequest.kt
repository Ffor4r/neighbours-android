package com.udevapp.data.api.place.address

data class AddressRequest(

    var city: String? = String(),

    var street: String? = String(),

    var house: String? = String(),

    var apt: String? = String(),
)