package com.aslnstbk.democompose.auth.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aslnstbk.democompose.auth.presentation.login.LoginScreen
import com.aslnstbk.democompose.auth.presentation.registration.RegistrationScreen
import com.aslnstbk.democompose.global.presentation.states.RedirectActivityState

@Composable
fun authNavFlow(
    navController: NavHostController,
    redirectToActivity: (RedirectActivityState) -> Unit
) {
    NavHost(navController = navController, startDestination = AuthNavRoute.Login.route) {
        composable(route = AuthNavRoute.Login.route) {
            LoginScreen(navController, redirectToActivity)
        }
        composable(route = AuthNavRoute.Registration.route) {
            RegistrationScreen(navController, redirectToActivity)
        }
    }
}