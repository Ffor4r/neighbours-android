package com.udevapp.data.repository.user

import com.udevapp.data.api.Api
import com.udevapp.data.api.user.UserRequest
import com.udevapp.data.api.user.UserService
import com.udevapp.data.repository.base.BaseRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRemoteDataSource(private val api: Api) : BaseRemoteDataSource() {

    suspend fun post(user: UserRequest): Result<Any?> {
        val response = withContext(Dispatchers.IO) {
            api.create(
                UserService::class.java,
                null,
                null
            ).post(user)
        }

        return handleResponse(response)
    }
}