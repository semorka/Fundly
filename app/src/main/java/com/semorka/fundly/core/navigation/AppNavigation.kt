package com.semorka.fundly.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.semorka.fundly.core.data.DatastoreViewModel
import com.semorka.fundly.core.data.room.DatabaseViewModel
import com.semorka.fundly.core.features.expense.presentation.ExpenseScreen
import com.semorka.fundly.core.features.home.presentation.HomeScreen
import com.semorka.fundly.core.features.menu.presentation.MenuScreen

@Composable fun AppNavigation(
    backStack: List<Screen>,
    onBack: () -> Unit
){
    NavDisplay(
        backStack = backStack,
        onBack = {onBack},
        entryProvider = { key ->
            when (key) {
                Screen.Home -> NavEntry(key) {
                    HomeScreen()
                }

                Screen.Menu -> NavEntry(key) {
                    MenuScreen()
                }

                Screen.Expense -> NavEntry(key) {
                    ExpenseScreen()
                }
            }
        }
    )
}