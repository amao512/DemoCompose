package com.aslnstbk.democompose.auth.data.models

data class RegistrationParam(
    val name: String,
    val surname: String,
    val phoneNumber: String,
    val email: String,
    val isDoctor: Boolean,
    var password: String
)
