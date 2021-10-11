package com.aslnstbk.democompose.auth.domain.repositories

import com.aslnstbk.democompose.auth.data.models.RegistrationParam
import com.aslnstbk.democompose.auth.data.models.UserDTO

interface AuthRepository {

    fun onLogin(
        email: String,
        password: String,
        onSuccess: (UserDTO) -> Unit,
        onFailure: () -> Unit
    )

    fun onRegistration(
        param: RegistrationParam,
        onSuccess: (UserDTO) -> Unit,
        onFailure: () -> Unit
    )

    fun signOut()
}