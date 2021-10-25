package com.aslnstbk.democompose.global.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.aslnstbk.democompose.global.presentation.navigation.mainFlow
import com.aslnstbk.democompose.global.presentation.ui.components.BottomNavigationBar
import com.aslnstbk.democompose.global.presentation.ui.theme.DemoComposeTheme

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
        mainFlow(navController)
    }
}

@Preview(showBackground = true)
@Composable
@ExperimentalMaterialApi
fun MainActivityPreview() {
    MainActivityView()
}