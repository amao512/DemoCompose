package com.aslnstbk.democompose.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.aslnstbk.democompose.global.data.Constants.EMPTY
import com.aslnstbk.democompose.global.presentation.navigation.NavigationItem
import com.aslnstbk.democompose.global.presentation.ui.theme.DemoComposeTheme
import com.aslnstbk.democompose.home.presentation.components.TakeTabletTime
import com.aslnstbk.democompose.home.presentation.components.WeekCalendar

@Composable
fun HomeScreen(navController: NavController) {
    Scaffold(
        modifier = Modifier
            .padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp
            )
            .background(color = Color.Black),
        floatingActionButton = {
            FloatingActionButton(
                onClick = { navController.navigate(NavigationItem.Search.route) },
                backgroundColor = MaterialTheme.colors.secondaryVariant,
                modifier = Modifier.padding(bottom = 56.dp)
            ) {
                Icon(Icons.Default.Add, contentDescription = EMPTY)
            }
        }
    ) {
        LazyColumn {
            item {
                WeekCalendar()
            }

            item {
                Box(modifier = Modifier.padding(top = 16.dp)) {
                    TakeTabletTime()
                }
            }

            item {
                Box(modifier = Modifier.padding(top = 16.dp)) {
                    TakeTabletTime()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    DemoComposeTheme(darkTheme = true) {
        HomeScreen(navController = rememberNavController())
    }
}