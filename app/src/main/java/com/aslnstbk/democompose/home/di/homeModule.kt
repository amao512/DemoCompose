package com.aslnstbk.democompose.home.di

import com.aslnstbk.democompose.home.presentation.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val homeModule = module {
    viewModel {
        HomeViewModel()
    }
}