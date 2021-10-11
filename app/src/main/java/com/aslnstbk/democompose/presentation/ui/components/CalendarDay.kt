package com.aslnstbk.democompose.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aslnstbk.democompose.domain.models.DayOfWeek

@Composable
fun CalendarDay(
    dayOfWeek: DayOfWeek
) {
    val roundedColor = if (dayOfWeek.isCurrentDay()) {
        MaterialTheme.colors.secondaryVariant
    } else {
        MaterialTheme.colors.primary
    }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = dayOfWeek.getShortDayOfWeekName(),
            fontSize = 12.sp
        )
        Box(
            modifier = Modifier.background(
                color = roundedColor,
                shape = RoundedCornerShape(100)
            )
        ) {
            Text(
                text = dayOfWeek.day.toString(),
                fontSize = 24.sp,
                modifier = Modifier.padding(4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalendarDayPreview() {
    CalendarDay(dayOfWeek = DayOfWeek("TUE", day = 24))
}