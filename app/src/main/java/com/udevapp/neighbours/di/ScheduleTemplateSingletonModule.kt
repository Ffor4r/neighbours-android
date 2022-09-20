package com.udevapp.neighbours.di

import com.udevapp.data.api.Api
import com.udevapp.data.repository.schedule_template.ScheduleTemplateRemoteDataSource
import com.udevapp.data.repository.schedule_template.ScheduleTemplateRepositoryImpl
import com.udevapp.domain.repository.ScheduleTemplateRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class ScheduleTemplateSingletonModule {

    @Provides
    fun provideScheduleTemplateRepository(scheduleTemplateRemoteDataSource: ScheduleTemplateRemoteDataSource): ScheduleTemplateRepository {
        return ScheduleTemplateRepositoryImpl(scheduleTemplateRemoteDataSource = scheduleTemplateRemoteDataSource)
    }

    @Provides
    fun provideScheduleTemplateRemoteDataSource(api: Api): ScheduleTemplateRemoteDataSource {
        return ScheduleTemplateRemoteDataSource(api = api)
    }
}