package com.aslnstbk.democompose.profile.domain.usecases

import com.aslnstbk.democompose.global.data.ResponseData
import com.aslnstbk.democompose.profile.domain.models.User
import com.aslnstbk.democompose.profile.domain.models.toUser
import com.aslnstbk.democompose.profile.domain.repositories.ProfileRepository

class GetProfileUseCase(
    private val profileRepository: ProfileRepository
) {

    operator fun invoke(
        uid: String,
        onResponse: (ResponseData<User, String>) -> Unit
    ) {
        profileRepository.getUser(
            uid = uid,
            onSuccess = {
                onResponse(ResponseData.Success(it.toUser()))
            },
            onFailure = {
                onResponse(ResponseData.Error(it))
            }
        )
    }
}