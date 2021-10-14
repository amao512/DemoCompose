package com.aslnstbk.democompose.global.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import org.koin.dsl.module

val applicationModule = module {

    single {
        FirebaseDatabase.getInstance()
    }

    single {
        val auth: FirebaseAuth = get()

        auth.currentUser
    }
}