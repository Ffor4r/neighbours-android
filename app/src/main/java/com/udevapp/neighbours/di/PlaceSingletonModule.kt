package com.udevapp.neighbours.di

import com.udevapp.data.api.Api
import com.udevapp.data.db.Database
import com.udevapp.data.repository.place.DefaultPlaceLocalDataSource
import com.udevapp.data.repository.place.DefaultPlaceRepositoryImpl
import com.udevapp.data.repository.place.PlaceRemoteDataSource
import com.udevapp.data.repository.place.PlaceRepositoryImpl
import com.udevapp.domain.repository.DefaultPlaceRepository
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
    fun provideDefaultPlaceLocalDataSource(database: Database): DefaultPlaceLocalDataSource {
        return DefaultPlaceLocalDataSource(database = database)
    }

    @Provides
    @Singleton
    fun provideDefaultPlaceRepository(defaultPlaceLocalDataSource: DefaultPlaceLocalDataSource): DefaultPlaceRepository {
        return DefaultPlaceRepositoryImpl(defaultPlaceLocalDataSource = defaultPlaceLocalDataSource)
    }

    @Provides
    @Singleton
    fun providePlaceRepository(
        remoteDataSource: PlaceRemoteDataSource,
    ): PlaceRepository {
        return PlaceRepositoryImpl(
            placeRemoteDataSource = remoteDataSource,
        )
    }

    @Provides
    @Singleton
    fun providePlaceRemoteDataSource(api: Api): PlaceRemoteDataSource {
        return PlaceRemoteDataSource(api = api)
    }

}