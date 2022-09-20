package com.udevapp.data.api.schedule_template

data class ScheduleTemplateRequest(

    var action: String? = String(),

    var days: ArrayList<String> = arrayListOf(),

    var place: String? = String()
)