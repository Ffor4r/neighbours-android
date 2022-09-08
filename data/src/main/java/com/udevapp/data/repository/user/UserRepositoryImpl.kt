package com.udevapp.data.repository.user

import com.udevapp.data.api.user.UserRequest
import com.udevapp.domain.repository.UserRepository

class UserRepositoryImpl(private val userRemoteDataSource: UserRemoteDataSource) : UserRepository {
    override suspend fun create(user: Any?): Result<Any?> =
        userRemoteDataSource.post(user = user as UserRequest)
}