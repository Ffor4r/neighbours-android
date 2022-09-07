package com.udevapp.data.api.mappers

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.udevapp.data.api.ApiError
import okhttp3.ResponseBody

class ApiErrorMapper {

    fun toError(errorBody: ResponseBody?): ApiError.Exception? {
        val type = object : TypeToken<ApiError.Exception>() {}.type
        return Gson().fromJson<ApiError.Exception?>(
            errorBody!!.charStream(),
            type
        )
    }

}