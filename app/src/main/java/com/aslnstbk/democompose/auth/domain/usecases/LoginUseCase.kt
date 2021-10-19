package com.aslnstbk.democompose.auth.domain.usecases

import com.aslnstbk.democompose.auth.domain.repositories.AuthRepository
import com.aslnstbk.democompose.profile.domain.models.User
import com.aslnstbk.democompose.profile.domain.models.toUser

class LoginUseCase(
    private val authRepository: AuthRepository
) {

    operator fun invoke(
        email: String,
        password: String,
        onSuccess: (User) -> Unit,
        onFailure: () -> Unit
    ) {
        authRepository.onLogin(
            email = email,
            password = password,
            onSuccess = {
                onSuccess(it.toUser())
            },
            onFailure = onFailure
        )
    }
}