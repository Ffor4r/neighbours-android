package com.udevapp.domain.repository

import com.udevapp.domain.model.UserToken

interface LoginRepository {

    suspend fun login(token: String): Result<Any?>

    fun saveToken(token: String)

    fun getToken(): UserToken?

    fun logout()

}