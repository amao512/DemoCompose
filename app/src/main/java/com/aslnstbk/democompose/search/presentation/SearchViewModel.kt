package com.aslnstbk.democompose.search.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aslnstbk.democompose.global.data.ResponseData
import com.aslnstbk.democompose.global.presentation.utils.toLowerCase
import com.aslnstbk.democompose.profile.domain.models.User
import com.aslnstbk.democompose.search.domain.usecases.GetAllUsersUseCase

class SearchViewModel(
    private val getAllUsersUseCase: GetAllUsersUseCase
) : ViewModel() {

    private val _allUsers: MutableLiveData<ResponseData<List<User>, String>> = MutableLiveData()
    val allUsers: LiveData<ResponseData<List<User>, String>> = _allUsers

    init {
        getAllUsers()
    }

    fun onSearch(query: String) {
        getAllUsersUseCase {
            when (it) {
                is ResponseData.Success -> {
                    _allUsers.value = ResponseData.Success(
                        it.data.filter { user ->
                            user.name.toLowerCase().contains(query)
                                || user.surname.toLowerCase().contains(query)
                                || user.email.toLowerCase().contains(query)
                        }
                    )
                }
                is ResponseData.Error -> {
                    _allUsers.value = it
                }
            }
        }
    }

    private fun getAllUsers() {
        getAllUsersUseCase {
            _allUsers.value = it
        }
    }
}