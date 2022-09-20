package com.udevapp.domain.usecase

import com.udevapp.domain.repository.DefaultPlaceRepository

class DefaultPlaceUseCase(private val repository: DefaultPlaceRepository) {

    suspend fun getDefaultPlace(userId: String): Result<Any?> = repository.getDefaultPlace(userId)

    suspend fun setDefaultPlace(userId: String, index: Int, placeId: String) = repository.setDefaultPlace(userId, index, placeId)

}