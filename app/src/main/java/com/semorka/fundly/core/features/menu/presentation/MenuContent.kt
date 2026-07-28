package com.semorka.fundly.core.features.menu.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

@Composable
fun MenuContent(
    onNewFunds: (Int) -> Unit
) {
    var amountText by remember { mutableStateOf("") }

    Column {
        TextField(
            onValueChange = { newValue ->
                if (newValue.all { it.isDigit() }) {
                    amountText = newValue
                }
            },
            value = amountText
        )

        Button(
            onClick = {
                onNewFunds(amountText.toInt())
            }) {
            Text("Set funds")
        }
    }
}

@Composable private fun MenuContentPreview()  = MenuContent {}