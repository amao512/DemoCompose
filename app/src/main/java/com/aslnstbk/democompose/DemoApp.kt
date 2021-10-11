package com.aslnstbk.democompose

import android.app.Application
import com.aslnstbk.democompose.auth.di.authModule
import com.aslnstbk.democompose.di.applicationModule
import org.koin.core.context.startKoin

class DemoApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                applicationModule,
                authModule
            )
        }
    }
}