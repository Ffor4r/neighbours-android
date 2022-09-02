package com.udevapp.data.api.mappers

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.udevapp.data.api.ApiError
import okhttp3.ResponseBody

class ApiErrorMapper {

    fun toError(errorBody: ResponseBody?): ApiError.Error? {
        val type = object : TypeToken<ApiError.Error>() {}.type
        return Gson().fromJson<ApiError.Error?>(
            errorBody!!.charStream(),
            type
        )
    }

}