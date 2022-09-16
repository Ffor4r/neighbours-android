package com.udevapp.data.repository.place

import com.udevapp.data.api.place.PlaceRequest
import com.udevapp.domain.repository.PlaceRepository

class PlaceRepositoryImpl(
    private val placeRemoteDataSource: PlaceRemoteDataSource
) :
    PlaceRepository {

    override suspend fun post(place: Any?): Result<Any?> =
        placeRemoteDataSource.post(place as PlaceRequest)

    override suspend fun get(): Result<Any?> = placeRemoteDataSource.get()

    override suspend fun put(id: String, place: Any?): Result<Any?> =
        placeRemoteDataSource.put(id, place as PlaceRequest)
}