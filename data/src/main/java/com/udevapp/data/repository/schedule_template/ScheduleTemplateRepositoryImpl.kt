package com.udevapp.data.repository.schedule_template

import com.udevapp.data.api.schedule_template.ScheduleTemplateRequest
import com.udevapp.domain.repository.ScheduleTemplateRepository

class ScheduleTemplateRepositoryImpl(private val scheduleTemplateRemoteDataSource: ScheduleTemplateRemoteDataSource) :
    ScheduleTemplateRepository {
    override suspend fun post(scheduleTemplate: Any?): Result<Any?> =
        scheduleTemplateRemoteDataSource.post(scheduleTemplate as ScheduleTemplateRequest)

    override suspend fun get(place: String?, days: String?): Result<Any?> =
        scheduleTemplateRemoteDataSource.get(place, days)

    override suspend fun put(id: String, scheduleTemplateRequest: Any): Result<Any?> =
        scheduleTemplateRemoteDataSource.put(id, scheduleTemplateRequest as ScheduleTemplateRequest)

    override suspend fun delete(id: String): Result<Any?> =
        scheduleTemplateRemoteDataSource.delete(id)
}