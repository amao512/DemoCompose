package com.aslnstbk.democompose.search.domain.usecases

import com.aslnstbk.democompose.global.data.ResponseData
import com.aslnstbk.democompose.profile.domain.models.User
import com.aslnstbk.democompose.profile.domain.models.toUser
import com.aslnstbk.democompose.search.domain.repositories.SearchRepository

class GetAllUsersUseCase(
    private val repository: SearchRepository
) {

    operator fun invoke(onResponse: (ResponseData<List<User>, String>) -> Unit) {
        repository.getAllUsers(
            onSuccess = {
                onResponse(ResponseData.Success(it.map { user -> user.toUser() }))
            },
            onFailure = {
                onResponse(ResponseData.Error(it))
            }
        )
    }
}