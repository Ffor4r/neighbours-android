package com.udevapp.domain.usecase

import com.udevapp.domain.repository.PlaceRepository

class CreatePlaceUseCase(private val repository: PlaceRepository) {
    suspend fun create(place: Any?) = repository.create(place = place)
}