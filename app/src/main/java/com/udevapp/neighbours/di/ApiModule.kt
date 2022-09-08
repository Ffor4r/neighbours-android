package com.udevapp.neighbours.di

import com.udevapp.data.api.Api
import com.udevapp.data.repository.login.LoginLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun provideApi(loginLocalDataSource: LoginLocalDataSource): Api {
        return Api(loginLocalDataSource = loginLocalDataSource)
    }

}