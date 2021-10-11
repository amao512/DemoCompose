package com.aslnstbk.democompose.presentation.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.aslnstbk.democompose.presentation.ui.components.TakeTabletTime
import com.aslnstbk.democompose.presentation.ui.components.WeekCalendar

@Composable
fun HomeScreen() {
    Scaffold(
        modifier = Modifier
            .padding(
                top = 16.dp,
                start = 16.dp,
                end = 16.dp
            )
            .background(color = Color.Black)
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
    HomeScreen()
}