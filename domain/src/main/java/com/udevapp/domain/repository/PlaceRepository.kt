package com.udevapp.domain.repository

interface PlaceRepository {

    suspend fun post(
        place: Any?
    ): Result<Any?>

    suspend fun get(): Result<Any?>

    suspend fun put(id: String, place: Any?): Result<Any?>
}