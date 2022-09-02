package com.example.domain.repository

import com.example.domain.model.User

interface UserRepository {
    suspend fun createUser(
        postUserData: User
    ): Any
}