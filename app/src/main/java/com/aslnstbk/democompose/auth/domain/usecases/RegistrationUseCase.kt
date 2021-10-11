package com.aslnstbk.democompose.auth.domain.usecases

import com.aslnstbk.democompose.auth.data.models.RegistrationParam
import com.aslnstbk.democompose.auth.domain.models.User
import com.aslnstbk.democompose.auth.domain.models.toUser
import com.aslnstbk.democompose.auth.domain.repositories.AuthRepository

class RegistrationUseCase(
    private val repository: AuthRepository
) {

    operator fun invoke(
        param: RegistrationParam,
        onSuccess: (User) -> Unit,
        onFailure: () -> Unit
    ) {
        repository.onRegistration(
            param = param,
            onSuccess = {
                onSuccess(it.toUser())
            },
            onFailure = onFailure
        )
    }
}