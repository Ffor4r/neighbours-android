package com.udevapp.data.api.schedule_template

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface ScheduleTemplateService {

    @POST("schedule-templates")
    suspend fun post(@Body scheduleTemplateRequest: ScheduleTemplateRequest): Response<ScheduleTemplateResponse>

    @GET("schedule-templates")
    suspend fun get(): Response<List<ScheduleTemplateResponse>>
}