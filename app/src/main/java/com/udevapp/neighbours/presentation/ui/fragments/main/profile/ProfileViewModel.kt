package com.udevapp.neighbours.presentation.ui.fragments.main.profile

import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udevapp.data.api.place.PlaceResponse
import com.udevapp.data.db.entity.DefaultPlace
import com.udevapp.data.repository.settings.SettingsLocalDataSource
import com.udevapp.domain.usecase.DefaultPlaceUseCase
import com.udevapp.domain.usecase.GetCurrentUserUseCase
import com.udevapp.domain.usecase.LoginUserUseCase
import com.udevapp.neighbours.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val loginUserUseCase: LoginUserUseCase,
    private val settingsLocalDataSource: SettingsLocalDataSource,
    private val currentUserUseCase: GetCurrentUserUseCase,
    private val getDefaultPlaceUseCase: DefaultPlaceUseCase
) : BaseViewModel() {

    private val _defaultPlace = MutableLiveData<DefaultPlace>()
    val defaultPlace: LiveData<DefaultPlace> = _defaultPlace

    fun logout() = loginUserUseCase.logout()

    fun loadDefaultPlace() {
        switchLoadingStatus()
        viewModelScope.launch {
            val result = getDefaultPlaceUseCase.getDefaultPlace(currentUser.id)
            _defaultPlace.value = result.getOrNull() as DefaultPlace?
            switchLoadingStatus()
        }
    }

    val currentUser = currentUserUseCase.getUserToken()!!

    var darkMode: Int
        get() {
            return settingsLocalDataSource.darkMode
        }
        set(value) {
            settingsLocalDataSource.darkMode = value
            AppCompatDelegate.setDefaultNightMode(value)
        }
}