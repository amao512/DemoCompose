package com.aslnstbk.democompose.auth.presentation.registration

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.aslnstbk.democompose.auth.data.models.RegistrationParam
import com.aslnstbk.democompose.auth.domain.usecases.RegistrationUseCase
import com.aslnstbk.democompose.global.states.RedirectActivityState
import com.aslnstbk.democompose.global.constants.PackageActivityConstants
import com.aslnstbk.democompose.global.states.ShowErrorState

class RegistrationViewModel(
    private val registrationUseCase: RegistrationUseCase
) : ViewModel() {

    private val _showErrorState = mutableStateOf(ShowErrorState())
    val showErrorState: State<ShowErrorState> = _showErrorState

    private val _redirectToActivityState = mutableStateOf(RedirectActivityState())
    val redirectActivityState: State<RedirectActivityState>
        get() = _redirectToActivityState

    fun onRegister(param: RegistrationParam) {
        registrationUseCase(
            param = param,
            onSuccess = {
                Log.d("TAG4", "user=$it")
                _redirectToActivityState.value = RedirectActivityState(
                    isRedirect = true,
                    activity = PackageActivityConstants.PKG_MAIN_ACTIVITY
                )
            },
            onFailure = {}
        )
    }
}