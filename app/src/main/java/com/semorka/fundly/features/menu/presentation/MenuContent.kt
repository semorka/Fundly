package com.semorka.fundly.features.menu.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.semorka.fundly.core.ui.theme.FundlyTheme

@Composable
fun MenuContent(
    onNewFunds: (Double) -> Unit
) {
    var amountText by remember { mutableStateOf("") }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
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
                onNewFunds(amountText.toDouble())
            }) {
            Text("Set funds")
        }
    }
}

@Preview
@Composable
private fun MenuContentPreview() {
    FundlyTheme {
        MenuContent {}
    }
}