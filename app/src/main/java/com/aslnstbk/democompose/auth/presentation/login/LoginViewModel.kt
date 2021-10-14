package com.aslnstbk.democompose.auth.presentation.login

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.aslnstbk.democompose.auth.domain.usecases.LoginUseCase
import com.aslnstbk.democompose.global.presentation.states.RedirectActivityState
import com.aslnstbk.democompose.global.presentation.constants.PackageActivityConstants

class LoginViewModel(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _redirectToActivity = mutableStateOf(RedirectActivityState())
    val redirectToActivity: State<RedirectActivityState>
        get() = _redirectToActivity

    fun onSignIn(
        email: String,
        password: String
    ) {
        loginUseCase(
            email = email,
            password = password,
            onSuccess = {
                Log.d("TAG4", "user=$it")
                _redirectToActivity.value = RedirectActivityState(
                    isRedirect = true,
                    activity = PackageActivityConstants.PKG_MAIN_ACTIVITY
                )
            },
            onFailure = {}
        )
    }
}