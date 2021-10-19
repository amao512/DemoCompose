package com.aslnstbk.democompose.profile.presentation

import com.aslnstbk.democompose.profile.domain.models.User

data class GetProfileState(
    val user: User? = null,
    val error: String = ""
)
