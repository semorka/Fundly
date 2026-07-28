package com.semorka.fundly.core.navigation

import com.semorka.fundly.R
import kotlinx.serialization.Serializable

@Serializable
sealed interface Screen {
    sealed interface BottomNavTab : Screen {
        val title: String
        val iconRes: Int
    }
    @Serializable
    data object Home : BottomNavTab {
        override val title = "Main"
        override val iconRes = R.drawable.ic_home
    }
    @Serializable
    data object Menu : BottomNavTab {
        override val title = "Menu"
        override val iconRes = R.drawable.ic_menu
    }
    @Serializable
    data object Expense : BottomNavTab {
        override val title = "Expense"
        override val iconRes = R.drawable.ic_expense
    }
}

val allTabs = listOf(
    Screen.Home,
    Screen.Menu,
    Screen.Expense
)