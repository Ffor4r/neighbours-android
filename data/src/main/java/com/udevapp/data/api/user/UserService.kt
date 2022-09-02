package com.udevapp.data.api.user

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface  UserService {

    @GET("users/{id}")
    suspend fun get(@Path(value = "id") name: String): Response<UserResponse>

    @POST("users")
    suspend fun post(
        @Body postUserRequest: UserRequest
    ): Response<UserResponse>

}