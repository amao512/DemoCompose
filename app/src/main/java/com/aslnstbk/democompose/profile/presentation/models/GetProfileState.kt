package com.aslnstbk.democompose.profile.presentation.models

import com.aslnstbk.democompose.profile.domain.models.User

data class GetProfileState(
    val user: User? = null,
    val error: String = ""
)
