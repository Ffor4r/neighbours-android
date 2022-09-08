package com.udevapp.neighbours.presentation.ui.fragments.sign.up

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.udevapp.data.api.user.UserRequest
import com.udevapp.data.api.user.UserResponse
import com.udevapp.domain.usecase.CreateUserUseCase
import com.udevapp.neighbours.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val createUserUseCase: CreateUserUseCase
) : BaseViewModel() {

    private val _user = MutableLiveData<UserResponse>()
    val user: LiveData<UserResponse> = _user

    val userFormData: UserRequest = UserRequest()

    fun createUser() {
        viewModelScope.launch {
            switchLoadingStatus()

            onSuccess(createUserUseCase.createUser(userFormData)) { _user.value = it as UserResponse }
        }
    }
}