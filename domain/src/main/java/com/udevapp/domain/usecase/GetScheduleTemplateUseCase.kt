package com.udevapp.domain.usecase

import com.udevapp.domain.repository.ScheduleTemplateRepository

class GetScheduleTemplateUseCase(private val scheduleTemplateRepository: ScheduleTemplateRepository) {
    suspend fun get(place: String? = null, days: String? = null) =
        scheduleTemplateRepository.get(place, days)
}