package com.udevapp.domain.usecase

import com.udevapp.domain.repository.ScheduleTemplateRepository

class DeleteScheduleTemplateUseCase(private val scheduleTemplateRepository: ScheduleTemplateRepository) {
    suspend fun delete(id: String) = scheduleTemplateRepository.delete(id)
}