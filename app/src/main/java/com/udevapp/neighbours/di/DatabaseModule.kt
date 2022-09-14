package com.udevapp.neighbours.di

import android.content.Context
import com.udevapp.data.api.Api
import com.udevapp.data.db.Database
import com.udevapp.data.repository.login.LoginLocalDataSource
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): Database {
        return Database(context = context)
    }
}