package com.semorka.fundly.core.navigation

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.semorka.fundly.features.home.presentation.HomeScreen

@Composable fun AppNavigation(backStack: List<Screen>, onBack: () -> Unit){
    NavDisplay(
        backStack = backStack,
        onBack = {onBack},
        entryProvider = { key ->
            when (key) {
                Screen.Home -> NavEntry(key) {
                    HomeScreen()
                }

                Screen.Menu -> NavEntry(key) {
                    Text("Меню")
                }
            }
        }
    )
}