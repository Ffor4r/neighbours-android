package com.udevapp.data.repository.login

import com.udevapp.domain.repository.LoginRepository

class LoginRepositoryImpl(
    private val loginRemoteDataSource: LoginRemoteDataSource,
    private val loginLocalDataSource: LoginLocalDataSource
) : LoginRepository {
    override suspend fun login(token: String): Result<Any?> = loginRemoteDataSource.login(token)

    override fun saveToken(token: String) = loginLocalDataSource.saveToken(token)

    override fun getToken(): String? = loginLocalDataSource.getToken()

    override fun logout() = loginLocalDataSource.deleteToken()
}