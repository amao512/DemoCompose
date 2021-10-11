package com.aslnstbk.democompose.auth.data.repositories

import com.aslnstbk.democompose.auth.data.firebase.AuthenticationFirebaseDataSource
import com.aslnstbk.democompose.auth.data.models.RegistrationParam
import com.aslnstbk.democompose.auth.data.models.UserDTO
import com.aslnstbk.democompose.auth.domain.repositories.AuthRepository

class DefaultAuthRepository(
    private val authenticationFirebaseDataSource: AuthenticationFirebaseDataSource
) : AuthRepository {

    override fun onLogin(
        email: String,
        password: String,
        onSuccess: (UserDTO) -> Unit,
        onFailure: () -> Unit
    ) {
        authenticationFirebaseDataSource.onLogin(email, password, onSuccess, onFailure)
    }

    override fun onRegistration(
        param: RegistrationParam,
        onSuccess: (UserDTO) -> Unit,
        onFailure: () -> Unit
    ) {
        authenticationFirebaseDataSource.onRegister(param, onSuccess, onFailure)
    }

    override fun signOut() {
        authenticationFirebaseDataSource.signOut()
    }
}