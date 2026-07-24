package com.semorka.fundly.core

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp

@Composable fun DefaultText(text: String, fontSize: Int){
    Text(
        text = text,
        fontSize = fontSize.sp
    )
}