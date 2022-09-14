package com.udevapp.domain.repository

interface PlaceRepository {

    suspend fun create(
        place: Any?
    ): Result<Any?>

    suspend fun get(): Result<Any?>
}