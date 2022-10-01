package com.udevapp.neighbours.di

import com.udevapp.domain.repository.UserRepository
import com.udevapp.domain.usecase.CreateUserUseCase
import com.udevapp.domain.usecase.UpdateUserNotificationTokenUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class UserModule {

    @Provides
    fun provideCreateUserUseCase(userRepository: UserRepository): CreateUserUseCase {
        return CreateUserUseCase(repository = userRepository)
    }

    @Provides
    fun provideUpdateUserNotificationTokenUseCase(userRepository: UserRepository): UpdateUserNotificationTokenUseCase {
        return UpdateUserNotificationTokenUseCase(repository = userRepository)
    }
}