package com.aslnstbk.democompose.profile.domain.usecases

import com.aslnstbk.democompose.global.data.ResponseData
import com.aslnstbk.democompose.profile.domain.repositories.ProfileRepository

class AddUserUseCase(
    private val repository: ProfileRepository
) {

    operator fun invoke(
        userId: String,
        onResponse: (ResponseData<Unit, String>) -> Unit
    ) {
        repository.addUser(
            userId = userId,
            onSuccess = {
                onResponse(ResponseData.Success(Unit))
            },
            onFailure = {
                onResponse(ResponseData.Error(it))
            }
        )
    }
}