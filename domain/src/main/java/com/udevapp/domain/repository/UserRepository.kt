package com.udevapp.domain.repository

interface UserRepository {
    suspend fun create(
        user: Any?
    ): Result<Any?>

    suspend fun put(
        id: String, user: Any?
    ): Result<Any?>
}