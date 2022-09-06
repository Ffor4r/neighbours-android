package com.udevapp.domain.repository

interface LoginRepository {

    suspend fun login(token: String): Result<Any?>

    fun saveToken(token: String)

    fun getToken(): String?

    fun logout()

}