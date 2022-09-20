package com.udevapp.data.repository.place

import com.udevapp.data.api.user.UserResponse
import com.udevapp.data.db.Database
import com.udevapp.data.db.entity.DefaultPlace
import com.udevapp.data.repository.base.BaseLocalDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class DefaultPlaceLocalDataSource(val database: Database) : BaseLocalDataSource() {

    suspend fun getDefaultPlaceIndex(userId: String): Result<Any?> {
        val result = withContext(Dispatchers.IO) {
            database.neighbours.defaultPlaceDao().findByUserId(userId)
        }

        return handleResult(result)
    }

    suspend fun getDefaultPlaceIndex(userResponse: UserResponse): Result<Any?> {
        val result = withContext(Dispatchers.IO) {
            database.neighbours.defaultPlaceDao().findByUserId(userResponse.id)
        }

        return handleResult(result)
    }

    suspend fun setDefaultPlaceIndex(defaultPlace: DefaultPlace) {
        withContext(Dispatchers.IO) {
            database.neighbours.defaultPlaceDao().insert(defaultPlace)
        }
    }

    suspend fun setDefaultPlaceIndex(userId: String, index: Int, placeId: String) {
        withContext(Dispatchers.IO) {
            database.neighbours.defaultPlaceDao()
                .insert(DefaultPlace(userId = userId, defaultIndex = index, placeId = placeId))
        }
    }
}

