package com.aslnstbk.democompose.profile.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aslnstbk.democompose.global.data.ResponseData
import com.aslnstbk.democompose.profile.domain.models.User
import com.aslnstbk.democompose.profile.domain.usecases.GetProfileUseCase
import com.google.firebase.auth.FirebaseUser

class ProfileViewModel(
    private val firebaseUser: FirebaseUser,
    private val getProfileUseCase: GetProfileUseCase
) : ViewModel() {

    private val _profileState: MutableLiveData<ResponseData<User, String>> = MutableLiveData()
    val profileState: LiveData<ResponseData<User, String>> get() = _profileState

    fun getProfile(uid: String?) {
        getProfileUseCase(
            uid = uid ?: firebaseUser.uid,
            onResponse = {
                _profileState.value = it
            }
        )
    }
}