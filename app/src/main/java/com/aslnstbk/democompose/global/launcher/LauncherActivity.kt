package com.aslnstbk.democompose.global.launcher

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import com.aslnstbk.democompose.auth.presentation.AuthActivity

class LauncherActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startActivity(
            Intent(this, AuthActivity::class.java)
        )
    }
}