package com.aslnstbk.democompose.global.presentation.launcher

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.aslnstbk.democompose.auth.presentation.AuthActivity
import com.aslnstbk.democompose.home.presentation.MainActivity
import com.google.firebase.auth.FirebaseUser
import org.koin.android.ext.android.inject

class LauncherActivity : ComponentActivity() {

    private val currentUser: FirebaseUser by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val intent = if (currentUser.uid.isNotBlank()) {
            Intent(this, MainActivity::class.java)
        } else {
            Intent(this, AuthActivity::class.java)
        }

        startActivity(intent)
    }
}