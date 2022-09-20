package com.udevapp.data.api.schedule_template

import com.google.gson.annotations.SerializedName
import com.udevapp.data.api.user.UserResponse

data class ScheduleTemplateResponse(

    @SerializedName("id")
    var id: Int,

    @SerializedName("action")
    var action: String,

    @SerializedName("days")
    var days: Set<Int>,

    @SerializedName("performers")
    var performers: List<UserResponse>

)