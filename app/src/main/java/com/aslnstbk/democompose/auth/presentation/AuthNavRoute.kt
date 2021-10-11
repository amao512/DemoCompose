package com.aslnstbk.democompose.auth.presentation

sealed class AuthNavRoute(val route: String) {
    object Login : AuthNavRoute(route = "/login")
    object Registration : AuthNavRoute(route = "/registration")
}
