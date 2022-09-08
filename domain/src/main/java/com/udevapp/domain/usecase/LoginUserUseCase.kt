package com.udevapp.domain.usecase

import com.udevapp.domain.repository.LoginRepository

class LoginUserUseCase(private val loginRepository: LoginRepository) {

    suspend fun login(token: String): Result<Any?> {
        if (loginRepository.getToken() == null) {
            val result = loginRepository.login(token)
            if (result.isSuccess) {
                loginRepository.saveToken(result.getOrNull().toString())
            }
            return result
        }

        return Result.success(loginRepository.getToken())
    }

    fun isLogin(): Boolean {
        return loginRepository.getToken() != null
    }

    fun logout() = loginRepository.logout()

}