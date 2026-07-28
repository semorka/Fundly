package com.semorka.fundly.core.utils

import java.util.Locale

fun Double.trimZeroDecimal(): String {
    return if (this % 1.0 == 0.0) {
        this.toInt().toString()
    } else {
        String.format(Locale.getDefault(), "%.1f", this)
    }
}