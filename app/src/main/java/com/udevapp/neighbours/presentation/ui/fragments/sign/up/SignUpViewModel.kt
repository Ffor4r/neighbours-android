package com.udevapp.neighbours.presentation.ui.fragments.sign.up

import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.udevapp.data.api.ApiError
import com.udevapp.domain.model.User
import com.udevapp.domain.usecase.CreateUserUseCase
import com.udevapp.domain.usecase.LoginUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val createUserUseCase: CreateUserUseCase
) : ViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    val createUserFormData: User = User()

    private val _error = MutableLiveData<ApiError>()
    val error: LiveData<ApiError> = _error

    fun createUser() {
        viewModelScope.launch {
            val result = createUserUseCase.createUser(createUserFormData)

            result.fold(
                {_user.value = it as User},
                {_error.value = it as ApiError?}
            )
        }
    }
}