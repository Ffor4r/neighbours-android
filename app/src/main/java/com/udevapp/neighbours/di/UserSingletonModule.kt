package com.udevapp.neighbours.di

import com.udevapp.data.api.Api
import com.udevapp.data.mappers.UserMapper
import com.udevapp.data.repository.user.UserRemoteDataSource
import com.udevapp.data.repository.user.UserRepositoryImpl
import com.udevapp.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UserSingletonModule {

    @Provides
    @Singleton
    fun provideUserRepository(userRemoteDataSource: UserRemoteDataSource): UserRepository {
        return UserRepositoryImpl(userRemoteDataSource = userRemoteDataSource)
    }

    @Provides
    @Singleton
    fun provideUserRemoteDataSource(api: Api, mapper: UserMapper): UserRemoteDataSource {
        return UserRemoteDataSource(api = api, mapper = mapper)
    }

    @Provides
    @Singleton
    fun provideUserMapper(): UserMapper {
        return UserMapper()
    }
}