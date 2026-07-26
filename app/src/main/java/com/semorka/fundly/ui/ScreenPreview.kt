package com.semorka.fundly.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.semorka.fundly.core.navigation.BottomBar
import com.semorka.fundly.core.navigation.Screen

@Composable fun ScreenPreview(screen: @Composable () -> Unit, barScreen: Screen = Screen.Home){
    var currentScreen by remember { mutableStateOf(barScreen) }
    Scaffold(
        bottomBar = {
            BottomBar(
                currentScreen = currentScreen,
                onTabSelected = { tab ->
                    currentScreen = tab
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            screen()
        }
    }
}