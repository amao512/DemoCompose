package com.aslnstbk.democompose.home.presentation

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
import com.aslnstbk.democompose.global.presentation.navigation.NavigationItem
import com.aslnstbk.democompose.global.presentation.ui.components.BottomNavigationBar
import com.aslnstbk.democompose.global.presentation.ui.theme.DemoComposeTheme
import com.aslnstbk.democompose.profile.presentation.ProfileScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DemoComposeTheme(darkTheme = true) {
                Surface(color = MaterialTheme.colors.background) {
                    MainActivityView()
                }
            }
        }
    }
}

@Composable
fun MainActivityView() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavigationBar(navController) }
    ) {
        NavHost(navController = navController, startDestination = NavigationItem.Home.route) {
            composable(route = NavigationItem.Home.route) { HomeScreen() }
            composable(route = NavigationItem.Profile.route) { ProfileScreen() }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    MainActivityView()
}