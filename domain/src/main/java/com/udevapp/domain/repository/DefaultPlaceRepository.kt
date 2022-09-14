package com.udevapp.domain.repository

interface DefaultPlaceRepository {

    fun getDefaultPlaceIndex(userId: String): Int

    fun setDefaultPlaceIndex(userId: String, index: Int)

}