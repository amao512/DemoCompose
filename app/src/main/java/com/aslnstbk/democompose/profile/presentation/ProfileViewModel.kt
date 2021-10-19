package com.aslnstbk.democompose.profile.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.aslnstbk.democompose.global.data.ResponseData
import com.aslnstbk.democompose.profile.domain.models.User
import com.aslnstbk.democompose.profile.domain.usecases.GetProfileUseCase
import com.google.firebase.auth.FirebaseUser

class ProfileViewModel(
    private val firebaseUser: FirebaseUser,
    private val getProfileUseCase: GetProfileUseCase
) : ViewModel() {

    private val _profileState: MutableState<ResponseData<User, String>> = mutableStateOf(ResponseData.Success(User()))
    val profileState: State<ResponseData<User, String>> get() = _profileState

    init {
        getProfile()
    }

    private fun getProfile() {
        getProfileUseCase(
            uid = firebaseUser.uid,
            onResponse = {
                _profileState.value = it
            }
        )
    }
}