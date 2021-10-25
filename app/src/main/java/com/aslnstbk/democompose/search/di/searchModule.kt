package com.aslnstbk.democompose.search.di

import com.aslnstbk.democompose.search.data.firebase.UsersFirebaseDataSource
import com.aslnstbk.democompose.search.data.repositories.DefaultSearchRepository
import com.aslnstbk.democompose.search.domain.repositories.SearchRepository
import com.aslnstbk.democompose.search.domain.usecases.GetAllUsersUseCase
import com.aslnstbk.democompose.search.presentation.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val searchModule = module {
    factory {
        UsersFirebaseDataSource(get())
    }

    factory<SearchRepository> {
        DefaultSearchRepository(get())
    }

    factory {
        GetAllUsersUseCase(get())
    }

    viewModel {
        SearchViewModel(get())
    }
}