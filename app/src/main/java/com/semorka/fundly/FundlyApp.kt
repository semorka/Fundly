package com.semorka.fundly

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.semorka.fundly.core.navigation.AppNavigation
import com.semorka.fundly.core.navigation.BottomBar
import com.semorka.fundly.core.navigation.NavigationViewModel

@Preview
@Composable fun FundlyApp() {
    val navigationVm: NavigationViewModel = viewModel()
    Scaffold(
        bottomBar = {
            BottomBar(
                currentScreen = navigationVm.currentScreen,
                onTabSelected = { tab ->
                    navigationVm.navigateTo(tab)
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            AppNavigation(navigationVm.backStack) { navigationVm.onBack() }
        }
    }
}