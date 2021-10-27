package com.aslnstbk.democompose.auth.presentation.login.models

sealed class LoginAction {
    data class SignIn(
        val email: String,
        val password: String
    ) : LoginAction()
}
