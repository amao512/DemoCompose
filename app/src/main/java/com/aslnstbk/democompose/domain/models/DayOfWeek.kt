package com.aslnstbk.democompose.domain.models

import java.util.*

data class DayOfWeek(
    val dayOfWeek: String,
    val day: Int
) {
    fun getShortDayOfWeekName(): String {
        return dayOfWeek.substring(0, 3).toUpperCase(Locale.getDefault())
    }

    fun isCurrentDay(): Boolean {
        val date = Calendar.getInstance()
        val currentDay = date.get(Calendar.DAY_OF_MONTH)

        return currentDay == day
    }
}
