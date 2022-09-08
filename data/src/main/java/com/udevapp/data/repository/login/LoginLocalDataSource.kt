package com.udevapp.data.repository.login

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.udevapp.domain.model.UserToken
import com.udevapp.domain.service.JwtService

private const val LOGIN_SHARED_PREFERENCE = "neighbours_login_pref"
private const val KEY_TOKEN_SHARED_PREFERENCE = "neighbours_login_token"

class LoginLocalDataSource(context: Context) {

    private val sharedPreferences =
        context.getSharedPreferences(LOGIN_SHARED_PREFERENCE, Context.MODE_PRIVATE)


    fun saveToken(token: String) {
        sharedPreferences.edit().putString(KEY_TOKEN_SHARED_PREFERENCE, token).apply()
    }

    fun getToken(): UserToken? {
        val token = sharedPreferences.getString(KEY_TOKEN_SHARED_PREFERENCE, null)
        if (token != null) {
            val type = object : TypeToken<UserToken>() {}.type
            val userToken = Gson().fromJson<UserToken?>(
                JwtService().decodeToken(token),
                type
            )
            userToken.token = token
            return userToken
        }
        return null
    }

    fun deleteToken() {
        sharedPreferences.edit().putString(KEY_TOKEN_SHARED_PREFERENCE, null).apply()
    }

}