package com.udevapp.data.repository.place

import com.udevapp.domain.repository.DefaultPlaceRepository

class DefaultPlaceRepositoryImpl(
    private val defaultPlaceLocalDataSource: DefaultPlaceLocalDataSource

) : DefaultPlaceRepository {
    override suspend fun getDefaultPlace(userId: String): Result<Any?> =
        defaultPlaceLocalDataSource.getDefaultPlaceIndex(userId)

    override suspend fun setDefaultPlace(userId: String, index: Int) =
        defaultPlaceLocalDataSource.setDefaultPlaceIndex(userId, index)
}