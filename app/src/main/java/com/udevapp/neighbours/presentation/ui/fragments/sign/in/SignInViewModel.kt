package com.udevapp.neighbours.presentation.ui.fragments.sign.`in`

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.udevapp.domain.usecase.LoginUserUseCase
import com.udevapp.neighbours.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val loginUserUseCase: LoginUserUseCase) :
    BaseViewModel() {

    var email: String = String()
    var password: String = String()

    private val _loginState = MutableLiveData<String>()
    val loginState: LiveData<String> = _loginState

    fun login() {
        viewModelScope.launch {
            switchLoadingStatus()

            onSuccess(
                loginUserUseCase.login(
                    Base64.getEncoder()
                        .encodeToString("$email:$password".toByteArray())
                )
            ) { _loginState.value = it.toString() }

        }
    }

    fun logout() = loginUserUseCase.logout()
}