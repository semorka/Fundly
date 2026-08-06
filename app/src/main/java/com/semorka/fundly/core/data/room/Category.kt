package com.semorka.fundly.core.data.room

import androidx.annotation.DrawableRes
import com.semorka.fundly.R

enum class Category(
    val title: String,
    val iconRes: Int
) {
    FOOD("Food", R.drawable.ic_food),
    DEVICES("Devices", R.drawable.ic_devices)
}