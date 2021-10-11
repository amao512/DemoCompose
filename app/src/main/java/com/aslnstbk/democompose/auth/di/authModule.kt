package com.aslnstbk.democompose.auth.di

import com.aslnstbk.democompose.auth.data.firebase.AuthenticationFirebaseDataSource
import com.aslnstbk.democompose.auth.data.repositories.DefaultAuthRepository
import com.aslnstbk.democompose.auth.domain.repositories.AuthRepository
import com.aslnstbk.democompose.auth.domain.usecases.LoginUseCase
import com.aslnstbk.democompose.auth.domain.usecases.RegistrationUseCase
import com.aslnstbk.democompose.auth.presentation.login.LoginViewModel
import com.aslnstbk.democompose.auth.presentation.registration.RegistrationViewModel
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    viewModel {
        LoginViewModel(get())
    }

    viewModel {
        RegistrationViewModel(get())
    }

    single {
        FirebaseAuth.getInstance()
    }

    single {
        AuthenticationFirebaseDataSource(get(), get())
    }

    single<AuthRepository> {
        DefaultAuthRepository(get())
    }

    single {
        LoginUseCase(get())
    }

    single {
        RegistrationUseCase(get())
    }
}