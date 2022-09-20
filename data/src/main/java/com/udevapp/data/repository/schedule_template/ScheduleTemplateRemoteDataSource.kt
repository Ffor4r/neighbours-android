package com.udevapp.data.repository.schedule_template

import com.udevapp.data.api.Api
import com.udevapp.data.api.schedule_template.ScheduleTemplateRequest
import com.udevapp.data.api.schedule_template.ScheduleTemplateService
import com.udevapp.data.repository.base.BaseRemoteDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ScheduleTemplateRemoteDataSource(private val api: Api) : BaseRemoteDataSource() {

    suspend fun get(): Result<Any?> {
        val response = withContext(Dispatchers.IO) {
            api.create(ScheduleTemplateService::class.java).get()
        }

        return handleResponse(response)
    }

    suspend fun post(scheduleTemplateRequest: ScheduleTemplateRequest): Result<Any?> {
        val response = withContext(Dispatchers.IO) {
            api.create(ScheduleTemplateService::class.java).post(scheduleTemplateRequest)
        }

        return handleResponse(response)
    }

}