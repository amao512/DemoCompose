package com.aslnstbk.democompose.search.domain.repositories

import com.aslnstbk.democompose.profile.data.models.UserDTO

interface SearchRepository {

    fun getAllUsers(
        onSuccess: (List<UserDTO>) -> Unit,
        onFailure: (String) -> Unit
    )
}