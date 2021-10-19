package com.aslnstbk.democompose.profile.domain.models

import com.aslnstbk.democompose.global.data.Constants.EMPTY
import com.aslnstbk.democompose.profile.data.models.UserDTO

data class User(
    val id: String = EMPTY,
    val name: String = EMPTY,
    val surname: String = EMPTY,
    val email: String = EMPTY,
    val phoneNumber: String = EMPTY,
    val photoUrl: String = EMPTY
) {
    val displayName = "$name $surname"
}

fun UserDTO.toUser(): User {
    return User(
        id = id,
        name = name,
        surname = surname,
        email = email,
        phoneNumber = phoneNumber,
        photoUrl = photoUrl
    )
}
