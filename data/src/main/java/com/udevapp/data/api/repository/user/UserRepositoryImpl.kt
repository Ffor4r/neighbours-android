package com.udevapp.data.api.repository.user

import com.udevapp.domain.model.User
import com.udevapp.domain.repository.UserRepository

class UserRepositoryImpl(private val userRemoteDataSource: UserRemoteDataSource) : UserRepository {
    override suspend fun createUser(postUserData: User): Result<Any?> =
        userRemoteDataSource.postUser(postUserData = postUserData)
}