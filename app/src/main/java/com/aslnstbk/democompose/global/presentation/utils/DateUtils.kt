package com.aslnstbk.democompose.global.presentation.utils

import org.threeten.bp.DayOfWeek
import org.threeten.bp.LocalDate
import org.threeten.bp.LocalTime
import org.threeten.bp.YearMonth
import org.threeten.bp.format.DateTimeFormatter
import java.util.*

object CalendarUtils {
    var selectedDate: LocalDate? = null

    fun getCurrentDayAndMonth(): String {
        val date = Calendar.getInstance()
        val month = date.get(Calendar.MONTH)
        val day = date.get(Calendar.DAY_OF_MONTH)

        return "$day ${getMonth(month)}"
    }

    fun formattedDate(date: LocalDate): String {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy")

        return date.format(formatter)
    }

    fun formattedTime(time: LocalTime): String {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a")

        return time.format(formatter)
    }

    fun monthYearFromDate(date: LocalDate): String {
        val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("MMMM yyyy")

        return date.format(formatter)
    }

    fun daysInMonthArray(date: LocalDate?): ArrayList<LocalDate?> {
        val daysInMonthArray: ArrayList<LocalDate?> = ArrayList()

        selectedDate?.let {
            val yearMonth: YearMonth = YearMonth.from(date)
            val daysInMonth: Int = yearMonth.lengthOfMonth()
            val firstOfMonth: LocalDate? = it.withDayOfMonth(1)
            val dayOfWeek: Int = firstOfMonth?.dayOfWeek?.value ?: 0

            for (i in 1..42) {
                if (i <= dayOfWeek || i > daysInMonth + dayOfWeek) {
                    daysInMonthArray.add(null)
                } else daysInMonthArray.add(
                    LocalDate.of(it.year, it.month, i - dayOfWeek)
                )
            }
        }

        return daysInMonthArray
    }

    fun daysInWeekArray(selectedDate: LocalDate): ArrayList<LocalDate?> {
        val days: ArrayList<LocalDate?> = ArrayList()
        var current: LocalDate = sundayForDate(selectedDate)
        val endDate: LocalDate = current.plusWeeks(1)

        while (current.isBefore(endDate)) {
            days.add(current)
            current = current.plusDays(1)
        }

        return days
    }

    private fun sundayForDate(current: LocalDate): LocalDate {
        var localDate: LocalDate = current
        val oneWeekAgo: LocalDate = localDate.minusWeeks(1)

        while (localDate.isAfter(oneWeekAgo)) {
            if (localDate.dayOfWeek == DayOfWeek.SUNDAY) return localDate

            localDate = localDate.minusDays(1)
        }

        return localDate
    }

    private fun getMonth(month: Int): String {
        return when (month) {
            0 -> "January"
            1 -> "February"
            2 -> "March"
            3 -> "April"
            4 -> "May"
            5 -> "June"
            6 -> "July"
            7 -> "August"
            8 -> "September"
            9 -> "October"
            10 -> "November"
            11 -> "December"
            else -> ""
        }
    }
}