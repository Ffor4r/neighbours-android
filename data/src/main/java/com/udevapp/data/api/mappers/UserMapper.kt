package com.udevapp.data.api.mappers

import com.udevapp.data.api.user.UserRequest
import com.udevapp.data.api.user.UserResponse
import com.udevapp.domain.model.User

class UserResponseMapper {

    fun toUser(userResponse: UserResponse): User {
        return User(
            userResponse.id,
            userResponse.firstName,
            userResponse.lastName,
            userResponse.email,
            userResponse.createdAt,
            null
        )
    }

    fun toUserRequest(user: User): PostUserRequest {
        return PostUserRequest(
            email = user.email,
            firstname = user.firstName,
            lastname = user.lastName,
            password = user.password!!
        )
    }

}