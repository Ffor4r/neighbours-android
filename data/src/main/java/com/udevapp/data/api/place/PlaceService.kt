package com.udevapp.data.api.place

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PlaceService {

    @POST("places")
    suspend fun post(@Body placeRequest: PlaceRequest): Response<PlaceResponse>

    @GET("places")
    suspend fun get(): Response<List<PlaceResponse>>

}