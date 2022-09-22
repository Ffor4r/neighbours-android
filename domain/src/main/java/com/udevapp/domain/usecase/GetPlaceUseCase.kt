package com.udevapp.domain.usecase

import com.udevapp.domain.repository.PlaceRepository

class GetPlaceUseCase(private val repository: PlaceRepository) {
    suspend fun get() = repository.get()

    suspend fun get(id: String) = repository.get(id)
}