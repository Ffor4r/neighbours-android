package com.udevapp.neighbours.presentation.ui.fragments.main.profile

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import com.udevapp.data.repository.settings.SettingsLocalDataSource
import com.udevapp.domain.usecase.LoginUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase,
    private val settingsLocalDataSource: SettingsLocalDataSource
) : ViewModel() {

    fun logout() = loginUserUseCase.logout()

    var darkMode: Int
        get() {
            return settingsLocalDataSource.darkMode
        }
        set(value) {
            settingsLocalDataSource.darkMode = value
            AppCompatDelegate.setDefaultNightMode(value)
        }
}