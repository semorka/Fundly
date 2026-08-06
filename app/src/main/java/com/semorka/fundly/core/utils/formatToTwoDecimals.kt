package com.semorka.fundly.core.utils

import java.util.Locale

fun Double.formatToTwoDecimals(): String {
    return String.format(Locale.US, "%.2f", this)
}