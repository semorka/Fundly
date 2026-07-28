package com.semorka.fundly.core.utils

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout

fun Modifier.ignoreHeight(): Modifier = this.layout { measurable, constraints ->
    val placeable = measurable.measure(constraints)
    layout(placeable.width, 0) {
        placeable.placeRelative(0, 0)
    }
}