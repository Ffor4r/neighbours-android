package com.udevapp.data.mappers

import com.udevapp.data.api.user.UserRequest
import com.udevapp.data.api.user.UserResponse
import com.udevapp.domain.model.User

class UserMapper {

    fun toUser(userResponse: UserResponse): User {
        return User(
            userResponse.id,
            userResponse.firstName,
            userResponse.lastName,
            userResponse.email,
            userResponse.createdAt,
            ""
        )
    }

    fun toUserRequest(user: User): UserRequest {
        return UserRequest(
            email = user.email,
            firstname = user.firstName,
            lastname = user.lastName,
            password = user.password,
        )
    }
}