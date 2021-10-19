package com.aslnstbk.democompose.profile.domain.repositories

import com.aslnstbk.democompose.profile.data.models.UserDTO

interface ProfileRepository {

    fun getUser(
        uid: String,
        onSuccess: (UserDTO) -> Unit,
        onFailure: (String) -> Unit
    )
}