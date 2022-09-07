package com.udevapp.neighbours.presentation.ui.fragments.sign.up

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.udevapp.domain.model.User
import com.udevapp.domain.usecase.CreateUserUseCase
import com.udevapp.neighbours.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    private val createUserUseCase: CreateUserUseCase
) : BaseViewModel() {

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> = _user

    val createUserFormData: User = User()

    fun createUser() {
        viewModelScope.launch {
            switchLoadingStatus()

            onSuccess(createUserUseCase.createUser(createUserFormData)) { _user.value = it as User }
        }
    }
}