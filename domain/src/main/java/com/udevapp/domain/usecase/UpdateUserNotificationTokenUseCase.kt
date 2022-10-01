package com.udevapp.domain.usecase

import com.udevapp.domain.repository.UserRepository

class UpdateUserNotificationTokenUseCase(private val repository: UserRepository) {
    suspend fun put(id: String, user: Any) = repository.put(id, user)
}