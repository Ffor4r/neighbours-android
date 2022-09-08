package com.udevapp.domain.repository

interface UserRepository {
    suspend fun create(
        user: Any?
    ): Result<Any?>
}