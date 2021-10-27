package com.aslnstbk.democompose.auth.presentation.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aslnstbk.democompose.auth.domain.usecases.LoginUseCase
import com.aslnstbk.democompose.auth.presentation.login.models.LoginAction
import com.aslnstbk.democompose.global.data.EventHandler
import com.aslnstbk.democompose.global.presentation.states.RedirectActivityState
import com.aslnstbk.democompose.global.presentation.utils.constants.PackageActivityConstants

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel(), EventHandler<LoginAction> {

    private val _redirectToActivity: MutableLiveData<RedirectActivityState> = MutableLiveData()
    val redirectToActivity: LiveData<RedirectActivityState> get() = _redirectToActivity

    override fun obtainEvent(event: LoginAction) {
        when (event) {
            is LoginAction.SignIn -> onSignIn(
                email = event.email,
                password = event.password
            )
        }
    }

    private fun onSignIn(
        email: String,
        password: String
    ) {
        loginUseCase(
            email = email,
            password = password,
            onSuccess = {
                _redirectToActivity.value = RedirectActivityState(
                    isRedirect = true,
                    activity = PackageActivityConstants.PKG_MAIN_ACTIVITY
                )
            },
            onFailure = {}
        )
    }
}