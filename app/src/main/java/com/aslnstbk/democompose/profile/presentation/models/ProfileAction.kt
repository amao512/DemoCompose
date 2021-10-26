package com.aslnstbk.democompose.profile.presentation.models

sealed class ProfileAction {
    data class GetProfile(val uid: String?) : ProfileAction()
    data class AddUser(val uid: String) : ProfileAction()
    object Exit : ProfileAction()
}