package com.udevapp.data.api.place

import com.udevapp.data.api.place.address.AddressRequest

data class PlaceRequest(

    var user: String? = String(),

    var title: String? = String(),

    var address: AddressRequest? = null,

    var members: Set<String>? = setOf()
)