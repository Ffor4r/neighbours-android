package com.udevapp.domain.usecase

import com.udevapp.domain.repository.ScheduleTemplateRepository

class EditScheduleTemplateUseCase(private val scheduleTemplateRepository: ScheduleTemplateRepository) {
    suspend fun put(id: String, scheduleTemplateRequest: Any) =
        scheduleTemplateRepository.put(id, scheduleTemplateRequest)
}