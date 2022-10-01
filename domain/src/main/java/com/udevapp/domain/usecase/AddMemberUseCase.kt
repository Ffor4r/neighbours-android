package com.udevapp.domain.usecase

import com.udevapp.domain.repository.PlaceRepository

class AddMemberUseCase(private val repository: PlaceRepository) {

    suspend fun addMember(id: String, member: String) = repository.addMember(id, member)

}