package com.udevapp.neighbours.di


import com.udevapp.domain.repository.LoginRepository
import com.udevapp.domain.usecase.LoginUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class LoginModule {

    @Provides
    fun provideLoginUserUseCase(loginRepository: LoginRepository): LoginUserUseCase{
        return LoginUserUseCase(loginRepository = loginRepository)
    }
}