package com.udevapp.neighbours.presentation.ui.fragments.main.home

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.messaging.FirebaseMessaging
import com.udevapp.data.api.place.PlaceRequest
import com.udevapp.data.api.place.PlaceResponse
import com.udevapp.data.api.user.UserRequest
import com.udevapp.data.db.entity.DefaultPlace
import com.udevapp.domain.usecase.*
import com.udevapp.neighbours.presentation.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getPlaceUseCase: GetPlaceUseCase,
    private val defaultPlaceUseCase: DefaultPlaceUseCase,
    private val getCurrentUserUseCase: GetCurrentUserUseCase,
    private val addMemberUseCase: AddMemberUseCase,
    private val updateUserNotificationTokenUseCase: UpdateUserNotificationTokenUseCase
) : BaseViewModel() {

    private val _place = MutableLiveData<PlaceResponse>()
    val place: LiveData<PlaceResponse> = _place

    fun addMember(id: String) {
        viewModelScope.launch {
            switchLoadingStatus()
            onSuccess(
                addMemberUseCase.addMember(
                    id,
                    getCurrentUserUseCase.getUserToken()!!.id
                )
            ) {
                loadPlace()
            }
        }
    }

    fun updateNotificationUserToken() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener(OnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@OnCompleteListener
            }
            val token = task.result

            viewModelScope.launch {
                switchLoadingStatus()
                onSuccess(
                    updateUserNotificationTokenUseCase.put(
                        getCurrentUserUseCase.getUserToken()!!.id,
                        UserRequest(notificationToken = token)
                    )
                ) {

                }
            }

        })
    }

    fun loadPlace() {
        viewModelScope.launch {
            switchLoadingStatus()
            val defaultPlace =
                defaultPlaceUseCase.getDefaultPlace(getCurrentUserUseCase.getUserToken()!!.id)
                    .getOrNull() as DefaultPlace?
            if (defaultPlace != null) {
                onSuccess(getPlaceUseCase.get(defaultPlace.placeId!!)) {
                    viewModelScope.launch {
                        if (place.value?.id?.equals((it as PlaceResponse).id) == false || place.value == null) {
                            _place.value = it as PlaceResponse
                        }
                    }
                }
            } else {
                onSuccess(getPlaceUseCase.get()) {
                    viewModelScope.launch {
                        val places = it as List<PlaceResponse>
                        _place.value = places.first()
                        defaultPlaceUseCase.setDefaultPlace(
                            getCurrentUserUseCase.getUserToken()!!.id,
                            0,
                            places.first().id
                        )
                    }
                }
            }
        }
    }
}