package com.udevapp.neighbours.di

import com.udevapp.domain.repository.UserRepository
import com.udevapp.domain.usecase.CreateUserUseCase
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
}