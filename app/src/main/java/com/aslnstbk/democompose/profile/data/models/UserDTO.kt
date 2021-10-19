package com.aslnstbk.democompose.profile.data.models

import com.aslnstbk.democompose.global.data.Constants

data class UserDTO(
    var id: String = Constants.EMPTY,
    var name: String = Constants.EMPTY,
    var surname: String = Constants.EMPTY,
    var email: String = Constants.EMPTY,
    var phoneNumber: String = Constants.EMPTY,
    var photoUrl: String = Constants.EMPTY,
    var isDoctor: Boolean = false
)
