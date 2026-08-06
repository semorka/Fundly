package com.semorka.fundly.core.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(timestamp: Long): String {
    val format = SimpleDateFormat("MMM d, HH:mm a", Locale.US)
    return format.format(Date(timestamp))
}