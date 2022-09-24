package com.udevapp.neighbours.di

import android.content.Context
import com.udevapp.data.repository.settings.SettingsLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext

@Module
@InstallIn(ViewModelComponent::class)
class SettingsModule {

    @Provides
    fun provideSettingsLocalDataSource(@ApplicationContext context: Context): SettingsLocalDataSource {
        return SettingsLocalDataSource(context)
    }

}