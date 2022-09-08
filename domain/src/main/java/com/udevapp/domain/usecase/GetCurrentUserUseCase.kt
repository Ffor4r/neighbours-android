package com.udevapp.domain.usecase

import com.udevapp.domain.model.UserToken
import com.udevapp.domain.repository.LoginRepository
import com.udevapp.domain.service.JwtService

class GetCurrentUserUseCase(private val loginRepository: LoginRepository) {

    fun getUserToken(): UserToken? = loginRepository.getToken()

}