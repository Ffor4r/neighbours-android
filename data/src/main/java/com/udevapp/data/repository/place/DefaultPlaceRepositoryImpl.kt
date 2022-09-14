package com.udevapp.data.repository.place

import com.udevapp.domain.repository.DefaultPlaceRepository

class DefaultPlaceRepositoryImpl(
    private val defaultPlaceLocalDataSource: DefaultPlaceLocalDataSource

) : DefaultPlaceRepository {
    override fun getDefaultPlaceIndex(userId: String): Int =
        defaultPlaceLocalDataSource.getDefaultPlaceIndex(userId).defaultIndex ?: 0

    override fun setDefaultPlaceIndex(userId: String, index: Int) =
        defaultPlaceLocalDataSource.setDefaultPlaceIndex(userId, index)
}