package com.udevapp.data.api.place

import com.udevapp.data.api.place.address.AddressRequest

data class PlaceRequest(

    val user: String? = String(),

    val address: AddressRequest
)