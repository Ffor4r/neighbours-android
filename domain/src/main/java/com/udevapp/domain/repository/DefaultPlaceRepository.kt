package com.udevapp.domain.repository

interface DefaultPlaceRepository {

    suspend fun getDefaultPlace(userId: String): Result<Any?>

    suspend fun setDefaultPlace(userId: String, index: Int)

}