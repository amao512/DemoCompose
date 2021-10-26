package com.aslnstbk.democompose.profile.domain.usecases

import com.aslnstbk.democompose.global.data.ResponseData
import com.aslnstbk.democompose.profile.domain.repositories.ProfileRepository

class SignOutUseCase(private val repository: ProfileRepository) {

    operator fun invoke(onResponse: (ResponseData<Unit, String>) -> Unit) {
        repository.onExit(
            onSuccess = {
                onResponse(ResponseData.Success(Unit))
            },
            onFailure = {
                onResponse(ResponseData.Error(it))
            }
        )
    }
}