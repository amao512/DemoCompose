package com.aslnstbk.democompose.auth.presentation.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aslnstbk.democompose.auth.data.models.RegistrationParam
import com.aslnstbk.democompose.auth.domain.usecases.RegistrationUseCase
import com.aslnstbk.democompose.auth.presentation.registration.models.RegistrationAction
import com.aslnstbk.democompose.global.data.EventHandler
import com.aslnstbk.democompose.global.presentation.states.RedirectActivityState
import com.aslnstbk.democompose.global.presentation.states.ShowErrorState
import com.aslnstbk.democompose.global.presentation.utils.constants.PackageActivityConstants

class RegistrationViewModel(
    private val registrationUseCase: RegistrationUseCase
) : ViewModel(), EventHandler<RegistrationAction> {

    private val _showErrorState: MutableLiveData<ShowErrorState> = MutableLiveData()
    val showErrorState: LiveData<ShowErrorState> get() = _showErrorState

    private val _redirectToActivityState: MutableLiveData<RedirectActivityState> = MutableLiveData()
    val redirectActivityState: LiveData<RedirectActivityState> get() = _redirectToActivityState

    override fun obtainEvent(event: RegistrationAction) {
        when (event) {
            is RegistrationAction.OnRegister -> onRegister(param = event.param)
        }
    }

    private fun onRegister(param: RegistrationParam) {
        registrationUseCase(
            param = param,
            onSuccess = {
                _redirectToActivityState.value = RedirectActivityState(
                    isRedirect = true,
                    activity = PackageActivityConstants.PKG_MAIN_ACTIVITY
                )
            },
            onFailure = {}
        )
    }
}