package com.udevapp.data.api.place

import retrofit2.Response
import retrofit2.http.*

interface PlaceService {

    @POST("places")
    suspend fun post(@Body placeRequest: PlaceRequest): Response<PlaceResponse>

    @GET("places")
    suspend fun get(): Response<List<PlaceResponse>>

    @GET("places/{id}")
    suspend fun get(@Path("id") id: String): Response<PlaceResponse>

    @PUT("places/{id}")
    suspend fun put(
        @Path("id") id: String,
        @Body placeRequest: PlaceRequest
    ): Response<PlaceResponse>

    @PATCH("places/{id}/add-member/{member}")
    suspend fun addMember(
        @Path("id") id: String,
        @Path("member") member: String
    ): Response<PlaceResponse>
}