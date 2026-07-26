package com.semorka.fundly.core.navigation

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel

class NavigationViewModel : ViewModel() {
    private val _backStack = mutableStateListOf<Screen>(Screen.Home)

    val backStack: List<Screen> get() = _backStack

    val currentScreen: Screen?
        get() = _backStack.lastOrNull()

    fun navigateTo(screen: Screen) {
        if (currentScreen != screen) {
            _backStack.remove(screen)
            _backStack.add(screen)
        }
    }

    fun onBack() {
        _backStack.removeLastOrNull()
    }
}