package com.semorka.fundly.core

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.semorka.fundly.core.navigation.Screen
import com.semorka.fundly.core.navigation.allScreens

@Composable fun ScreenPreview(screen: @Composable () -> Unit, barScreen: Screen = Screen.Home){
    val backStack = remember { mutableStateListOf(barScreen)}
    val currentScreen = backStack.lastOrNull()
    Scaffold(
        bottomBar = {
            NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
                allScreens.forEach { tab ->
                    NavigationBarItem(
                        selected = tab == currentScreen,
                        onClick = {},
                        icon = {
                            Icon(
                                painter = painterResource(tab.iconRes),
                                contentDescription = tab.title
                            )
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            screen()
        }
    }
}