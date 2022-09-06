package com.udevapp.neighbours.presentation.ui.fragments.sign.`in`

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udevapp.data.api.ApiError
import com.udevapp.domain.usecase.LoginUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(private val loginUserUseCase: LoginUserUseCase) :
    ViewModel() {

    var email: String = String()
    var password: String = String()

    private val _loginState = MutableLiveData<String>()
    val loginState: LiveData<String> = _loginState

    private val _error = MutableLiveData<ApiError>()
    val error: LiveData<ApiError> = _error

    fun login() {
        viewModelScope.launch {
            val result = loginUserUseCase.login(
                Base64.getEncoder()
                    .encodeToString("$email:$password".toByteArray())
            )

            result.fold(
                {_loginState.value = it.toString()},
                {_error.value = it as ApiError}
            )
        }
    }

    fun logout() = loginUserUseCase.logout()
}