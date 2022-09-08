package com.udevapp.neighbours.presentation.ui.fragments.main.profile

import androidx.lifecycle.ViewModel
import com.udevapp.domain.usecase.LoginUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(private val loginUserUseCase: LoginUserUseCase): ViewModel() {

    fun logout() = loginUserUseCase.logout()
    // TODO: Implement the ViewModel
}