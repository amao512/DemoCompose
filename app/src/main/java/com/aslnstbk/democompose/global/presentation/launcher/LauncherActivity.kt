package com.aslnstbk.democompose.global.presentation.launcher

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.aslnstbk.democompose.auth.presentation.AuthActivity
import com.aslnstbk.democompose.home.presentation.MainActivity
import com.google.firebase.auth.FirebaseAuth

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val user = FirebaseAuth.getInstance().currentUser

        user?.let {
            val intent = if (it.uid.isNotBlank()) {
                Intent(this, MainActivity::class.java)
            } else {
                Intent(this, AuthActivity::class.java)
            }

            startActivity(intent)
        } ?: run {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
        }
    }
}