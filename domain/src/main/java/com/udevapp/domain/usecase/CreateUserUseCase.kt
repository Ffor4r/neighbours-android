package com.udevapp.domain.usecase

import com.udevapp.domain.model.User
import com.udevapp.domain.repository.UserRepository

class CreateUserUseCase(private val repository: UserRepository) {
    suspend fun createUser(
        postUserData: User
    ) = repository.createUser(
        postUserData = postUserData
    )
}