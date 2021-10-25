package com.aslnstbk.democompose.global.presentation.launcher

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aslnstbk.democompose.auth.presentation.AuthActivity
import com.aslnstbk.democompose.global.presentation.MainActivity
import com.google.firebase.auth.FirebaseAuth

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val user = FirebaseAuth.getInstance().currentUser

        user?.let {
            if (it.uid.isNotBlank()) {
                startActivity(
                    Intent(this, MainActivity::class.java)
                )
            } else {
                startActivity(
                    Intent(this, AuthActivity::class.java)
                )
            }
        } ?: run {
            startActivity(
                Intent(this, AuthActivity::class.java)
            )
        }
    }
}