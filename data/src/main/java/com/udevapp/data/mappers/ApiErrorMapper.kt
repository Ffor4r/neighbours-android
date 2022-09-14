package com.udevapp.data.mappers

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.udevapp.data.api.Violation
import okhttp3.ResponseBody

class ApiErrorMapper {

    fun toError(errorBody: ResponseBody?): Violation.Exception? {
        val type = object : TypeToken<Violation.Exception>() {}.type
        return Gson().fromJson<Violation.Exception?>(
            errorBody!!.charStream(),
            type
        )
    }

}