package com.udevapp.domain.repository

interface PlaceRepository {

    suspend fun post(
        place: Any?
    ): Result<Any?>

    suspend fun get(): Result<Any?>

    suspend fun get(id: String): Result<Any?>

    suspend fun put(id: String, place: Any?): Result<Any?>

    suspend fun addMember(id: String, member: String): Result<Any?>
}