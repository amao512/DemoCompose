package com.aslnstbk.democompose.di

import com.google.firebase.database.FirebaseDatabase
import org.koin.dsl.module

val applicationModule = module {
    single {
        FirebaseDatabase.getInstance()
    }
}