package com.udevapp.domain.repository

interface ScheduleTemplateRepository {

    suspend fun post(
        scheduleTemplate: Any?
    ): Result<Any?>

    suspend fun get(): Result<Any?>


}