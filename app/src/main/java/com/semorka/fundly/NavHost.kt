package com.semorka.fundly

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay

data object Home
data object Screen2

@Composable fun AppNavigation(){
    val backStack = remember { mutableStateListOf<Any>(Home)}
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                is Home -> NavEntry(key) {
                    Button(onClick = {backStack.add(Screen2)}) {
                        Text("to screen2")
                    }
                }

                is Screen2 -> NavEntry(key) {
                    Button(onClick = {backStack.add(Home)}) {
                        Text("to home")
                    }
                }

                else -> NavEntry(Unit) {
                    Column {
                        Text("Error 404")
                        Text("This screen is not found")
                    }
                }
            }
        }
    )
}