package com.udevapp.neighbours.di

import com.udevapp.domain.repository.ScheduleTemplateRepository
import com.udevapp.domain.usecase.CreateScheduleTemplateUseCase
import com.udevapp.domain.usecase.DeleteScheduleTemplateUseCase
import com.udevapp.domain.usecase.EditScheduleTemplateUseCase
import com.udevapp.domain.usecase.GetScheduleTemplateUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class ScheduleTemplateModule {

    @Provides
    fun provideGetScheduleTemplateUseCase(scheduleTemplateRepository: ScheduleTemplateRepository): GetScheduleTemplateUseCase {
        return GetScheduleTemplateUseCase(scheduleTemplateRepository = scheduleTemplateRepository)
    }

    @Provides
    fun provideCreateScheduleTemplateUseCase(scheduleTemplateRepository: ScheduleTemplateRepository): CreateScheduleTemplateUseCase {
        return CreateScheduleTemplateUseCase(scheduleTemplateRepository = scheduleTemplateRepository)
    }

    @Provides
    fun provideEditScheduleTemplateUseCase(scheduleTemplateRepository: ScheduleTemplateRepository): EditScheduleTemplateUseCase {
        return EditScheduleTemplateUseCase(scheduleTemplateRepository = scheduleTemplateRepository)
    }

    @Provides
    fun provideDeleteScheduleTemplateUseCase(scheduleTemplateRepository: ScheduleTemplateRepository): DeleteScheduleTemplateUseCase {
        return DeleteScheduleTemplateUseCase(scheduleTemplateRepository = scheduleTemplateRepository)
    }
}