package com.udevapp.neighbours.di

import android.content.Context
import com.udevapp.data.api.Api
import com.udevapp.data.api.repository.login.LoginLocalDataSource
import com.udevapp.data.api.repository.login.LoginRemoteDataSource
import com.udevapp.data.api.repository.login.LoginRepositoryImpl
import com.udevapp.domain.repository.LoginRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LoginSingletonModule {

    @Provides
    @Singleton
    fun provideLoginLocalDataSource(@ApplicationContext context: Context): LoginLocalDataSource {
        return LoginLocalDataSource(context = context)
    }

    @Provides
    @Singleton
    fun provideLoginRemoteDataSource(api: Api): LoginRemoteDataSource {
        return LoginRemoteDataSource(api = api)
    }

    @Provides
    @Singleton
    fun provideLoginRepository(
        loginRemoteDataSource: LoginRemoteDataSource,
        loginLocalDataSource: LoginLocalDataSource
    ): LoginRepository {
        return LoginRepositoryImpl(
            loginRemoteDataSource = loginRemoteDataSource,
            loginLocalDataSource = loginLocalDataSource
        )
    }
}