package com.udevapp.domain.repository

interface ScheduleTemplateRepository {

    suspend fun post(
        scheduleTemplate: Any?
    ): Result<Any?>

    suspend fun get(place: String?): Result<Any?>

    suspend fun put(id: String, scheduleTemplateRequest: Any): Result<Any?>

    suspend fun delete(id: String): Result<Any?>
}