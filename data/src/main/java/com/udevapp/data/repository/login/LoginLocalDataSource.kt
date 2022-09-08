package com.udevapp.data.repository.login

import android.content.Context

private const val LOGIN_SHARED_PREFERENCE = "neighbours_login_pref"
private const val KEY_TOKEN_SHARED_PREFERENCE = "neighbours_login_token"

class LoginLocalDataSource(context: Context) {

    private val sharedPreferences =
        context.getSharedPreferences(LOGIN_SHARED_PREFERENCE, Context.MODE_PRIVATE)


    fun saveToken(token: String) {
        sharedPreferences.edit().putString(KEY_TOKEN_SHARED_PREFERENCE, token).apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString(KEY_TOKEN_SHARED_PREFERENCE, null)
    }

    fun deleteToken() {
        sharedPreferences.edit().putString(KEY_TOKEN_SHARED_PREFERENCE, null).apply()
    }

}