package com.aslnstbk.democompose.profile.data.repositories

import com.aslnstbk.democompose.profile.data.firebase.ProfileFirebaseDataSource
import com.aslnstbk.democompose.profile.data.models.UserDTO
import com.aslnstbk.democompose.profile.domain.repositories.ProfileRepository

class DefaultProfileRepository(
    private val dataSource: ProfileFirebaseDataSource
) : ProfileRepository {

    override fun getUser(
        uid: String,
        onSuccess: (UserDTO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        dataSource.getUserByUid(uid, onSuccess, onFailure
        )
    }
}