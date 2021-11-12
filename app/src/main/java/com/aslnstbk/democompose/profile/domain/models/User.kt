package com.aslnstbk.democompose.profile.domain.models

import com.aslnstbk.democompose.global.data.Constants.EMPTY
import com.aslnstbk.democompose.profile.data.models.UserDTO

data class User(
    val id: String = EMPTY,
    val name: String = EMPTY,
    val surname: String = EMPTY,
    val email: String = EMPTY,
    val phoneNumber: String = EMPTY,
    val photoUrl: String = EMPTY,
    val isDoctor: Boolean = false,
    val userPatients: List<String> = emptyList(),
    val userDoctors: List<String> = emptyList()
) {
    val displayName = "$name $surname"

    fun checkIfExists(userId: String): Boolean {
        return userPatients.any { it == userId } || userDoctors.any { it == userId }
    }
}

fun UserDTO.toUser(): User {
    return User(
        id = id.orEmpty(),
        name = name.orEmpty(),
        surname = surname.orEmpty(),
        email = email.orEmpty(),
        phoneNumber = phoneNumber.orEmpty(),
        photoUrl = photoUrl.orEmpty(),
        isDoctor = isDoctor,
        userPatients = userPatients ?: emptyList(),
        userDoctors = userDoctors ?: emptyList()
    )
}
