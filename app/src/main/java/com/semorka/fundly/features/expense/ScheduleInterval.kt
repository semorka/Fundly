package com.semorka.fundly.features.expense

enum class ScheduleInterval (
    val daysCount: Int,
    val title: String
) {
    EVERY_DAY(daysCount = 1, title = "Every day"),
    EVERY_WEEK(daysCount = 7, title = "Every week"),
    EVERY_MONTH(daysCount = 30, title = "Every month");

    companion object {
        val default = EVERY_DAY

        fun fromDays(days: Int): ScheduleInterval? {
            return entries.firstOrNull { it.daysCount == days }
        }
    }
}