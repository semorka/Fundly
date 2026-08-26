package com.semorka.fundly.features.expense.presentation

import androidx.lifecycle.ViewModel
import com.semorka.fundly.core.data.room.Category
import com.semorka.fundly.features.expense.ExpenseFormState
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

@HiltViewModel
class ExpenseViewModel @Inject constructor(): ViewModel() {
    private val _formState = MutableStateFlow(ExpenseFormState())
    val formState: StateFlow<ExpenseFormState> = _formState.asStateFlow()

    fun updateExpenseText(text: String) {
        _formState.update { it.copy(expenseText = text) }
    }

    fun toggleSchedule() {
        _formState.update { it.copy(scheduleChecked = !it.scheduleChecked)}
    }

    fun updateScheduleText(text: String) {
        _formState.update { it.copy(scheduleText = text)}
    }

    fun updateCategory(category: Category?) {
        _formState.update { it.copy(selectedCategory = category) }
    }
}