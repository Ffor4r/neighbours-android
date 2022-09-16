package com.udevapp.neighbours.di

import com.udevapp.domain.repository.DefaultPlaceRepository
import com.udevapp.domain.repository.PlaceRepository
import com.udevapp.domain.usecase.CreatePlaceUseCase
import com.udevapp.domain.usecase.DefaultPlaceUseCase
import com.udevapp.domain.usecase.EditAddressUseCase
import com.udevapp.domain.usecase.GetPlaceUseCase
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
    fun provideEditAddressUseCase(placeRepository: PlaceRepository): EditAddressUseCase {
        return EditAddressUseCase(repository = placeRepository)
    }

}