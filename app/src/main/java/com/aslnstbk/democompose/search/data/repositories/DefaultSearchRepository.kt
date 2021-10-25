package com.aslnstbk.democompose.search.data.repositories

import com.aslnstbk.democompose.profile.data.models.UserDTO
import com.aslnstbk.democompose.search.data.firebase.UsersFirebaseDataSource
import com.aslnstbk.democompose.search.domain.repositories.SearchRepository

class DefaultSearchRepository(
    private val usersFirebaseDataSource: UsersFirebaseDataSource
) : SearchRepository {

    override fun getAllUsers(
        onSuccess: (List<UserDTO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        usersFirebaseDataSource.getAllUsers(onSuccess, onFailure)
    }
}