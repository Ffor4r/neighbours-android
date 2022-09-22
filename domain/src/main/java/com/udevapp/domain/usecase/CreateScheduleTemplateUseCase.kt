package com.udevapp.domain.usecase

import com.udevapp.domain.repository.ScheduleTemplateRepository

class CreateScheduleTemplateUseCase(private val scheduleTemplateRepository: ScheduleTemplateRepository) {
    suspend fun post(scheduleTemplate: Any) = scheduleTemplateRepository.post(scheduleTemplate)
}