package com.aslnstbk.democompose.global.states

import android.os.Bundle

data class RedirectActivityState(
    val isRedirect: Boolean = false,
    val activity: String = "",
    val bundle: Bundle? = null
)
