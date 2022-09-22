package com.udevapp.data.api.schedule_template

import com.udevapp.data.api.user.UserResponse

data class ScheduleTemplateRequest(

    var action: String? = String(),

    var days: ArrayList<Int> = ArrayList(),

    var place: String? = String(),

    var performers: ArrayList<String> = ArrayList()
)