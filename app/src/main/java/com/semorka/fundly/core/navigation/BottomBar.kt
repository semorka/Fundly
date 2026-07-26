package com.semorka.fundly.core.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable fun BottomBar(currentScreen: Screen?, onTabSelected: (tab: Screen) -> Unit, modifier: Modifier = Modifier) {
    NavigationBar(windowInsets = NavigationBarDefaults.windowInsets) {
        allScreens.forEach { tab ->
            NavigationBarItem(
                selected = tab == currentScreen,
                onClick = {onTabSelected(tab)},
                icon = {
                    Icon(
                        painter = painterResource(tab.iconRes),
                        contentDescription = tab.title,
                        modifier = Modifier.scale(1.4f).padding(vertical = 12.dp, horizontal = 6.dp)
                    )
                }
            )
        }
    }
}

@Preview @Composable private fun BottomBarPreview() {
    var currentScreen by remember { mutableStateOf<Screen>(Screen.Home) }
    BottomBar(currentScreen = currentScreen, {tab ->
        currentScreen = tab
    })
}