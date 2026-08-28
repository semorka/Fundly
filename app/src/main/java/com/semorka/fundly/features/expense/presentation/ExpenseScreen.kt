package com.semorka.fundly.features.expense.presentation

import androidx.compose.runtime.Composable
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.semorka.fundly.core.data.room.DatabaseViewModel
import com.semorka.fundly.features.expense.viewmodel.ExpenseViewModel

@Composable
fun ExpenseScreen(
    dbViewModel : DatabaseViewModel = hiltViewModel(),
    expenseViewModel: ExpenseViewModel = hiltViewModel()
) {
    val state = expenseViewModel.formState.collectAsStateWithLifecycle().value

    ExpenseContent(
        onNewExpense = {
            dbViewModel.addExpense(
                cost = state.expenseText.toDoubleOrNull() ?: 0.0,
                name = state.expenseName,
                schedule = state.scheduleText.toIntOrNull(),
                category = state.selectedCategory
            ) },
        state = state,
        onExpenseNameChanged = { expenseViewModel.updateExpenseName(it) },
        onExpenseTextChanged = { expenseViewModel.updateExpenseText(it) },
        onScheduleToggled = { expenseViewModel.toggleSchedule() },
        onScheduleTextChanged = { expenseViewModel.updateScheduleText(it) },
        onCategoryUpdated = { expenseViewModel.updateCategory(it) }
    )
}