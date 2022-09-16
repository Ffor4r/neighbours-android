package com.udevapp.data.api.place

import retrofit2.Response
import retrofit2.http.*

interface PlaceService {

    @POST("places")
    suspend fun post(@Body placeRequest: PlaceRequest): Response<PlaceResponse>

    @GET("places")
    suspend fun get(): Response<List<PlaceResponse>>

    @PUT("places/{id}")
    suspend fun put( @Path("id") id: String, @Body placeRequest: PlaceRequest): Response<PlaceResponse>
}