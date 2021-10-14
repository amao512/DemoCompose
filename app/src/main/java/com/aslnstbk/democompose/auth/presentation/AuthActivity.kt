package com.aslnstbk.democompose.auth.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.aslnstbk.democompose.auth.presentation.login.LoginScreen
import com.aslnstbk.democompose.auth.presentation.registration.RegistrationScreen
import com.aslnstbk.democompose.global.presentation.states.RedirectActivityState
import com.aslnstbk.democompose.global.presentation.utils.showModuleActivity
import com.aslnstbk.democompose.global.presentation.ui.theme.DemoComposeTheme

class AuthActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            DemoComposeTheme(darkTheme = true) {
                Surface(color = MaterialTheme.colors.background) {
                    AuthScreen {
                        showModuleActivity(
                            path = it.activity,
                            args = it.bundle
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AuthScreen(redirectToActivity: (RedirectActivityState) -> Unit) {
    val navController = rememberNavController()

    Scaffold {
        NavHost(navController = navController, startDestination = AuthNavRoute.Login.route) {
            composable(route = AuthNavRoute.Login.route) {
                LoginScreen(navController, redirectToActivity)
            }
            composable(route = AuthNavRoute.Registration.route) {
                RegistrationScreen(navController, redirectToActivity)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AuthScreenPreview() {
    AuthScreen { }
}