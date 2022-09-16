package com.udevapp.data.geocoderapi.geocode

import retrofit2.http.GET
import retrofit2.http.Query

interface GeocodeService {

    @GET()
    suspend fun findByPosition(@Query("geocode")position: String)
}