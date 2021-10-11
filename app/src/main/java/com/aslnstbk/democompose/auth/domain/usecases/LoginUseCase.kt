package com.aslnstbk.democompose.auth.domain.usecases

import com.aslnstbk.democompose.auth.domain.models.User
import com.aslnstbk.democompose.auth.domain.models.toUser
import com.aslnstbk.democompose.auth.domain.repositories.AuthRepository

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