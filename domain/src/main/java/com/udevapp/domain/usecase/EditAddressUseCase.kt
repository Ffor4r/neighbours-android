package com.udevapp.domain.usecase

import com.udevapp.domain.repository.PlaceRepository

class EditAddressUseCase(private val repository: PlaceRepository) {
    suspend fun edit(id: String, place: Any?) = repository.put(id = id, place = place)
}