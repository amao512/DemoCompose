package com.aslnstbk.democompose.global.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.aslnstbk.democompose.home.presentation.HomeScreen
import com.aslnstbk.democompose.search.presentation.SearchScreen
import com.aslnstbk.democompose.profile.presentation.ProfileScreen

@Composable
fun mainFlow(
    navController: NavHostController
) {
    NavHost(navController = navController, startDestination = NavigationItem.Home.route) {
        composable(route = NavigationItem.Home.route) { HomeScreen(navController) }
        composable(route = NavigationItem.Profile.route) { ProfileScreen() }
        composable(route = NavigationItem.Search.route) { SearchScreen() }
    }
}