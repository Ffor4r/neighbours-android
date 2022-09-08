package com.udevapp.neighbours.di

import com.udevapp.data.api.Api
import com.udevapp.data.repository.place.PlaceRemoteDataSource
import com.udevapp.data.repository.place.PlaceRepositoryImpl
import com.udevapp.domain.repository.PlaceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PlaceSingletonModule {

    @Provides
    @Singleton
    fun providePlaceRepository(remoteDataSource: PlaceRemoteDataSource): PlaceRepository {
        return PlaceRepositoryImpl(placeRemoteDataSource = remoteDataSource)
    }

    @Provides
    @Singleton
    fun providePlaceRemoteDataSource(api: Api): PlaceRemoteDataSource {
        return PlaceRemoteDataSource(api = api)
    }

}