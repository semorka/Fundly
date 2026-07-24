package com.semorka.fundly.core.navigation

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.semorka.fundly.features.home.presentation.HomeScreen

@Composable fun AppNavigation(backStack: MutableList<Screen>){
    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryProvider = { key ->
            when (key) {
                Screen.Home -> NavEntry(key) {
                    HomeScreen()
                }

                Screen.Menu -> NavEntry(key) {
                    Button(onClick = {backStack.add(Screen.Home)}) {
                        Text("to home")
                    }
                }
            }
        }
    )
}