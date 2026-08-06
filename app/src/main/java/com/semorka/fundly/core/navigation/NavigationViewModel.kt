package com.semorka.fundly.core.navigation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class NavigationViewModel : ViewModel() {
    val backStack: List<Screen>
        field = mutableStateListOf<Screen>(Screen.Home)

    val currentScreen: Screen?
        get() = backStack.lastOrNull()

    fun navigateTo(screen: Screen) {
        if (currentScreen != screen) {
            backStack.remove(screen)
            backStack.add(screen)
        }
    }

    fun onBack() {
        backStack.removeLastOrNull()
    }
}