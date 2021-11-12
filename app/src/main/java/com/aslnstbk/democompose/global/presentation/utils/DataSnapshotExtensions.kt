package com.aslnstbk.democompose.global.presentation.utils

import com.aslnstbk.democompose.profile.data.models.UserDTO
import com.google.firebase.database.DataSnapshot

fun DataSnapshot.toUserDTO(): UserDTO {
    return UserDTO(
        id = child("id").value as String,
        name = child("name").value as String,
        surname = child("surname").value as String,
        email = child("email").value as String,
        phoneNumber = child("phoneNumber").value as String,
        photoUrl = child("photoUrl").value as String,
        isDoctor = child("doctor").value as Boolean,
        userPatients = child("userPatients").children.map { it.value as String },
        userDoctors = child("userDoctors").children.map { it.value as String }
    )
}