package com.udevapp.domain.usecase

import com.udevapp.domain.repository.UserRepository

class CreateUserUseCase(private val repository: UserRepository) {
    suspend fun createUser(
        user: Any
    ) = repository.create(
        user = user
    )
}