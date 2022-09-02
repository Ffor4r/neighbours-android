package com.udevapp.data.api.repository.user

import com.udevapp.domain.model.User
import com.udevapp.data.api.Api
import com.udevapp.data.api.ApiError
import com.udevapp.data.api.mappers.ApiErrorMapper
import com.udevapp.data.api.mappers.UserMapper
import com.udevapp.data.api.user.UserService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRemoteDataSource(
    private val api: Api,
    private val mapper: UserMapper
) {
    suspend fun postUser(postUserData: User): Result<Any?> {
        val response = withContext(Dispatchers.IO) {
                api.create(
                    UserService::class.java,
                    null,
                    null
                ).post(mapper.toUserRequest(postUserData))
        }

        return if (response.isSuccessful) {
            Result.success(response.body()?.let { mapper.toUser(it) })
        } else {
            Result.failure(ApiError(message = response.message(), responseBody = response.errorBody()))
        }
    }
}