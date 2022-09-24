package com.udevapp.data.repository.settings

import android.content.Context

class SettingsLocalDataSource(context: Context) {

    companion object {
        private const val SETTINGS_SHARED_PREFERENCE = "neighbours_settings_pref"

        private const val KEY_DARK_MODE = "neighbours_dark_mode"
        private const val KEY_LANGUAGE = "neighbours_language"
    }

    private val sharedPreferences =
        context.getSharedPreferences(SETTINGS_SHARED_PREFERENCE, Context.MODE_PRIVATE)

    var darkMode: Int
        get() {
            return sharedPreferences.getInt(KEY_DARK_MODE, -1)
        }
        set(value) {
            sharedPreferences.edit().putInt(KEY_DARK_MODE, value).apply()
        }

}