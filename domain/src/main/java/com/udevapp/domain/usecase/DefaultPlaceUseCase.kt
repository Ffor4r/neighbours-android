package com.udevapp.domain.usecase

import com.udevapp.domain.repository.DefaultPlaceRepository

class DefaultPlaceUseCase(private val repository: DefaultPlaceRepository) {

    fun getDefaultIndex(userId: String): Int = repository.getDefaultPlaceIndex(userId)

    fun setDefaultIndex(userId: String, index: Int) = repository.setDefaultPlaceIndex(userId, index)

}