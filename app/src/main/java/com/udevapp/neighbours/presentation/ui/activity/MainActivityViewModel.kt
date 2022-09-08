package com.udevapp.neighbours.presentation.ui.activity

import androidx.lifecycle.ViewModel
import com.udevapp.domain.usecase.LoginUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainActivityViewModel @Inject constructor(private val loginUserUseCase: LoginUserUseCase) : ViewModel() {

    fun isLogin() = loginUserUseCase.isLogin()

}