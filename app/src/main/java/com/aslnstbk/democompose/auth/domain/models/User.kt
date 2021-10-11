package com.aslnstbk.democompose.auth.domain.models

import com.aslnstbk.democompose.auth.data.models.UserDTO

data class User(
    val id: String,
    val displayName: String,
    val phoneNumber: String,
    val photoUrl: String,
    val email: String
)

fun UserDTO.toUser(): User {
    return User(
        id = id.orEmpty(),
        displayName = displayName.orEmpty(),
        phoneNumber = phoneNumber.orEmpty(),
        photoUrl = photoUrl.orEmpty(),
        email = email.orEmpty()
    )
}