package com.udevapp.neighbours.presentation.ui.activity

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.ViewModel
import com.udevapp.data.repository.settings.SettingsLocalDataSource
import com.udevapp.domain.usecase.LoginUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase,
    private val settingsLocalDataSource: SettingsLocalDataSource
) : ViewModel() {

    fun isLogin() = loginUserUseCase.isLogin()

    fun setupSettings() {
        AppCompatDelegate.setDefaultNightMode(settingsLocalDataSource.darkMode)
    }
}