package com.semorka.fundly.features.expense.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.semorka.fundly.core.data.room.DatabaseViewModel
import kotlin.math.exp

@Composable
fun ExpenseScreen(
    dbViewModel : DatabaseViewModel = hiltViewModel(),
    expenseViewModel: ExpenseViewModel = hiltViewModel()
) {
    val state = expenseViewModel.formState.collectAsStateWithLifecycle().value
    ExpenseContent(
        onNewExpense = { cost, name, schedule, category ->
            dbViewModel.addExpense(cost,name, schedule, category) },
        state = state,
        onExpenseTextChanged = { expenseViewModel.updateExpenseText(it) },
        onScheduleToggled = { expenseViewModel.toggleSchedule() },
        onScheduleTextChanged = { expenseViewModel.updateScheduleText(it) },
        onCategoryUpdated = { expenseViewModel.updateCategory(it) }
    )
}