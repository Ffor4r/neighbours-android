package com.udevapp.data.api.schedule_template

import retrofit2.Response
import retrofit2.http.*

interface ScheduleTemplateService {

    @POST("schedule-templates")
    suspend fun post(@Body scheduleTemplateRequest: ScheduleTemplateRequest): Response<ScheduleTemplateResponse>

    @GET("schedule-templates")
    suspend fun get(
        @Query("place") place: String?,
        @Query("days") days: String?
    ): Response<List<ScheduleTemplateResponse>>

    @PUT("schedule-templates/{id}")
    suspend fun put(
        @Path(value = "id") id: String?,
        @Body scheduleTemplateRequest: ScheduleTemplateRequest
    ): Response<ScheduleTemplateResponse>

    @DELETE("schedule-templates/{id}")
    suspend fun delete(@Path(value = "id") id: String?): Response<Int>
}