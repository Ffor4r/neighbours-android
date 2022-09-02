package com.udevapp.domain.repository

import com.udevapp.domain.model.User

interface UserRepository {
    suspend fun createUser(
        postUserData: User
    ): Result<Any?>
}