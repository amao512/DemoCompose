package com.aslnstbk.democompose.profile.presentation

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aslnstbk.democompose.global.data.EventHandler
import com.aslnstbk.democompose.global.data.ResponseData
import com.aslnstbk.democompose.global.presentation.states.RedirectActivityState
import com.aslnstbk.democompose.global.presentation.utils.constants.PackageActivityConstants
import com.aslnstbk.democompose.profile.domain.models.User
import com.aslnstbk.democompose.profile.domain.usecases.AddUserUseCase
import com.aslnstbk.democompose.profile.domain.usecases.GetProfileUseCase
import com.aslnstbk.democompose.profile.domain.usecases.SignOutUseCase
import com.aslnstbk.democompose.profile.presentation.models.ProfileAction
import com.google.firebase.auth.FirebaseUser

class ProfileViewModel(
    private val firebaseUser: FirebaseUser,
    private val getProfileUseCase: GetProfileUseCase,
    private val addUserUseCase: AddUserUseCase,
    private val signOutUseCase: SignOutUseCase
) : ViewModel(), EventHandler<ProfileAction> {

    private val _profileState: MutableLiveData<ResponseData<User, String>> = MutableLiveData()
    val profileState: LiveData<ResponseData<User, String>> get() = _profileState

    private val _isOwnProfile: MutableLiveData<Boolean> = MutableLiveData()
    val isOwnProfile: LiveData<Boolean> get() = _isOwnProfile

    private val _redirectToActivity: MutableLiveData<RedirectActivityState> = MutableLiveData()
    val redirectToActivity: LiveData<RedirectActivityState> get() = _redirectToActivity

    override fun obtainEvent(event: ProfileAction) {
        when (event) {
            is ProfileAction.GetProfile -> getProfile(uid = event.uid)
            is ProfileAction.AddUser -> addUser(userId = event.uid)
            is ProfileAction.Exit -> signOut()
        }
    }

    private fun getProfile(uid: String?) {
        getProfileUseCase(
            uid = uid ?: firebaseUser.uid,
            onResponse = {
                _profileState.value = it

                when (it) {
                    is ResponseData.Success -> {
                        _isOwnProfile.value = it.data.id == firebaseUser.uid
                    }
                    is ResponseData.Error -> {}
                }
            }
        )
    }

    private fun addUser(userId: String) {
        addUserUseCase(userId) {
            Log.d("TAG4", "response=$it")
        }
    }

    private fun signOut() {
        signOutUseCase {
            when (it) {
                is ResponseData.Success -> {
                    _redirectToActivity.value = RedirectActivityState(
                        isRedirect = true,
                        activity = PackageActivityConstants.PKG_AUTH_ACTIVITY
                    )
                }
                is ResponseData.Error -> {}
            }
        }
    }
}