package com.aslnstbk.democompose.global.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import org.koin.dsl.module

val applicationModule = module {

    factory {
        FirebaseDatabase.getInstance()
    }

    factory {
        val auth: FirebaseAuth = get()

        auth.currentUser
    }
}