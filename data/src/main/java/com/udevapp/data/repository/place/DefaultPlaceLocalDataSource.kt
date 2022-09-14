package com.udevapp.data.repository.place

import com.udevapp.data.api.user.UserResponse
import com.udevapp.data.db.Database
import com.udevapp.data.db.entity.DefaultPlace

class DefaultPlaceLocalDataSource(val database: Database) {

    fun getDefaultPlaceIndex(userId: String): DefaultPlace =
        database.neighbours.defaultPlaceDao().findByUserId(userId)

    fun getDefaultPlaceIndex(userResponse: UserResponse): DefaultPlace =
        database.neighbours.defaultPlaceDao().findByUserId(userResponse.id)

    fun setDefaultPlaceIndex(defaultPlace: DefaultPlace) {
        database.neighbours.defaultPlaceDao().insert(defaultPlace)
    }

    fun setDefaultPlaceIndex(userId: String, index: Int) {
        database.neighbours.defaultPlaceDao().insert(DefaultPlace(userId = userId, defaultIndex = index))
    }
}