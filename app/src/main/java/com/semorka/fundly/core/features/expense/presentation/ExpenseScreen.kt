package com.semorka.fundly.core.features.expense.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.semorka.fundly.core.data.room.DatabaseViewModel
import com.semorka.fundly.core.ui.theme.FundlyTheme

@Composable
fun ExpenseScreen(
    dbViewModel : DatabaseViewModel
) {
    ExpenseContent( onNewExpense = { cost, name, schedule, category ->
        dbViewModel.addExpense(cost,name, schedule, category)
    })
}