package com.udevapp.data.repository.place

import com.udevapp.data.api.place.PlaceRequest
import com.udevapp.domain.repository.PlaceRepository

class PlaceRepositoryImpl(
    private val placeRemoteDataSource: PlaceRemoteDataSource) :
    PlaceRepository {

    override suspend fun create(place: Any?): Result<Any?> =
        placeRemoteDataSource.post(place as PlaceRequest)

    override suspend fun get(): Result<Any?> = placeRemoteDataSource.get()
}