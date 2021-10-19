package com.aslnstbk.democompose.profile.di

import com.aslnstbk.democompose.profile.data.firebase.ProfileFirebaseDataSource
import com.aslnstbk.democompose.profile.data.repositories.DefaultProfileRepository
import com.aslnstbk.democompose.profile.domain.repositories.ProfileRepository
import com.aslnstbk.democompose.profile.domain.usecases.GetProfileUseCase
import com.aslnstbk.democompose.profile.presentation.ProfileViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val profileModule = module {
    factory {
        ProfileFirebaseDataSource(get())
    }

    factory<ProfileRepository> {
        DefaultProfileRepository(get())
    }

    factory {
        GetProfileUseCase(get())
    }

    viewModel {
        ProfileViewModel(get(), get())
    }
}