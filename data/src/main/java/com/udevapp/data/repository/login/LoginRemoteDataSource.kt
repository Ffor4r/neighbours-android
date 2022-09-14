package com.udevapp.data.repository.login

import com.udevapp.data.api.Api
import com.udevapp.data.api.Violation
import com.udevapp.data.api.login.LoginService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginRemoteDataSource(private val api: Api) {

    suspend fun login(basicAuthToken: String): Result<Any?> {
        val response = withContext(Dispatchers.IO) {
            api.create(
                LoginService::class.java,
                basicAuthToken,
                "Basic"
            ).login()
        }

        return if (response.isSuccessful) {
            Result.success(response.body()?.token)
        } else {
            Result.failure(Violation(message = response.message(), responseBody = response.errorBody()))
        }
    }

}