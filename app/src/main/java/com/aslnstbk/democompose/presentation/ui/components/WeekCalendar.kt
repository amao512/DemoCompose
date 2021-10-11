package com.aslnstbk.democompose.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.aslnstbk.democompose.domain.models.DayOfWeek
import com.aslnstbk.democompose.presentation.utils.CalendarUtils
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId

@Composable
fun WeekCalendar() {
    val list = mutableListOf<DayOfWeek>()

    CalendarUtils.selectedDate = LocalDate.now(ZoneId.of("UTC"))

    CalendarUtils.selectedDate?.let {
        CalendarUtils.daysInWeekArray(it).map { date ->
            list.add(
                DayOfWeek(
                    dayOfWeek = date?.dayOfWeek?.name.orEmpty(),
                    day = date?.dayOfMonth ?: 0
                )
            )
        }
    }

    Box(
        modifier = Modifier
            .background(
                color = MaterialTheme.colors.primary,
                shape = RoundedCornerShape(16.dp)
            )
            .padding(16.dp)
    ) {
        Column {
            Text(
                text = CalendarUtils.getCurrentDayAndMonth(),
                fontSize = 24.sp,
                modifier = Modifier.padding(bottom = 16.dp)
            )

            LazyRow(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                items(list) { day ->
                    CalendarDay(day)
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeekCalendarPreview() {
    WeekCalendar()
}