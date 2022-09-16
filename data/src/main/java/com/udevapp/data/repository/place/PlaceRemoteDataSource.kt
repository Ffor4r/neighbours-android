package com.udevapp.data.repository.place

import com.udevapp.data.api.Api
import com.udevapp.data.api.place.PlaceRequest
import com.udevapp.data.api.place.PlaceService
import com.udevapp.data.repository.base.BaseRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PlaceRemoteDataSource(private val api: Api) : BaseRemoteDataSource() {

    suspend fun get(): Result<Any?> {
        val response = withContext(Dispatchers.IO) {
            api.create(PlaceService::class.java).get()
        }

        return handleResponse(response)
    }

    suspend fun post(placeRequest: PlaceRequest): Result<Any?> {
        val response = withContext(Dispatchers.IO) {
            api.create(PlaceService::class.java).post(placeRequest)
        }

        return handleResponse(response)
    }

    suspend fun put(id: String, placeRequest: PlaceRequest): Result<Any?> {
        val response = withContext(Dispatchers.IO) {
            api.create(PlaceService::class.java).put(id, placeRequest)
        }

        return handleResponse(response)
    }

}