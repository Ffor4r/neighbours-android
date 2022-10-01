package com.udevapp.data.api.user

import retrofit2.Response
import retrofit2.http.*

interface UserService {

    @GET("users/{id}")
    suspend fun get(@Path(value = "id") name: String): Response<UserResponse>

    @PUT("users/{id}")
    suspend fun put(
        @Path(value = "id") id: String,
        @Body userRequest: UserRequest
    ): Response<UserResponse>

    @POST("users")
    suspend fun post(
        @Body postUserRequest: UserRequest
    ): Response<UserResponse>

}