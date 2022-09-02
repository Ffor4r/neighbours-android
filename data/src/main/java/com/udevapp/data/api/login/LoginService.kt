package com.udevapp.data.api.login

import retrofit2.Response
import retrofit2.http.GET

interface LoginService {

    @GET
    suspend fun login(): Response<LoginResponse>

}