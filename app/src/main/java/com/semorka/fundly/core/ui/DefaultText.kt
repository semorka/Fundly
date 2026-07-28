package com.semorka.fundly.core.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.sp

@Composable fun DefaultText(
    text: String,
    modifier: Modifier = Modifier,
    fontSize: Int = 14,
    fontFamily: FontFamily = FontFamily.Default){
    Text(
        text = text,
        fontSize = fontSize.sp,
        fontFamily = fontFamily,
        color = MaterialTheme.colorScheme.onSurface,
        modifier = Modifier
    )
}