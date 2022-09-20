package com.udevapp.data.repository.schedule_template

import com.udevapp.data.api.schedule_template.ScheduleTemplateRequest
import com.udevapp.domain.repository.ScheduleTemplateRepository

class ScheduleTemplateRepositoryImpl(private val scheduleTemplateRemoteDataSource: ScheduleTemplateRemoteDataSource) :
    ScheduleTemplateRepository {
    override suspend fun post(scheduleTemplate: Any?): Result<Any?> =
        scheduleTemplateRemoteDataSource.post(scheduleTemplate as ScheduleTemplateRequest)

    override suspend fun get(): Result<Any?> = scheduleTemplateRemoteDataSource.get()
}