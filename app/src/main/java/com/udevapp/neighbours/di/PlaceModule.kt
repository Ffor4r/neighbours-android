package com.udevapp.neighbours.di

import com.udevapp.domain.repository.DefaultPlaceRepository
import com.udevapp.domain.repository.PlaceRepository
import com.udevapp.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class PlaceModule {

    @Provides
    fun provideGetPlaceUseCase(placeRepository: PlaceRepository): GetPlaceUseCase {
        return GetPlaceUseCase(repository = placeRepository)
    }

    @Provides
    fun provideCreatePlaceUseCase(placeRepository: PlaceRepository): CreatePlaceUseCase {
        return CreatePlaceUseCase(repository = placeRepository)
    }

    @Provides
    fun provideDefaultPlaceUseCase(defaultPlaceRepository: DefaultPlaceRepository): DefaultPlaceUseCase {
        return DefaultPlaceUseCase(repository = defaultPlaceRepository)
    }

    @Provides
    fun provideEditPlaceUseCase(placeRepository: PlaceRepository): EditPlaceUseCase {
        return EditPlaceUseCase(repository = placeRepository)
    }

    @Provides
    fun provideAddMemberUseCase(placeRepository: PlaceRepository): AddMemberUseCase {
        return AddMemberUseCase(repository = placeRepository)
    }

}