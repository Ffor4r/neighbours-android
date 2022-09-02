package com.example.data.repository.user

import com.example.data.api.Api
import com.example.data.api.user.UserService
import com.example.data.mappers.UserResponseMapper
import com.example.domain.model.Error
import com.example.domain.model.User
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRemoteDataSource(
    private val api: Api,
    private val mapper: UserResponseMapper
) {

    suspend fun postUser(postUserData: User): Any =
        withContext(Dispatchers.IO) {
            try {
                val response = api.create(
                    UserService::class.java,
                    null,
                    null
                ).post(mapper.toUserRequest(postUserData))

                if (response.isSuccessful) {
                    return@withContext mapper.toUser(response.body()!!)
                } else {
                    val type = object : TypeToken<Error>() {}.type
                    return@withContext Gson().fromJson<Error?>(
                        response.errorBody()!!.charStream(),
                        type
                    )
                }
            } catch (e: Exception) {
                return@withContext Error(e.message)
            }
        }

}