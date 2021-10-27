package com.aslnstbk.democompose.auth.presentation.registration.models

import com.aslnstbk.democompose.auth.data.models.RegistrationParam

sealed class RegistrationAction {
    data class OnRegister(val param: RegistrationParam) : RegistrationAction()
}
