package com.aslnstbk.democompose.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.aslnstbk.democompose.presentation.home.HomeScreen
import com.aslnstbk.democompose.presentation.ui.theme.DemoComposeTheme

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
    HomeScreen()
}

@Preview(showBackground = true)
@Composable
fun MainActivityPreview() {
    MainActivityView()
}