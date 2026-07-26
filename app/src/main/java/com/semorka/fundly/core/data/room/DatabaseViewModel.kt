package com.semorka.fundly.core.data.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DatabaseViewModel @Inject constructor(
    private val dao: ExpenseDao
) : ViewModel() {
    val expenses: StateFlow<List<ExpenseEntity>> = dao.getAll()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    val allSum: Double
        get() = expenses.value.sumOf { it.cost }
    val percentLeft: Int
        get() = ((allSum / 60_000.0) * 100).toInt()

    fun addExpense(cost: Double, name: String) {
        viewModelScope.launch {
            dao.insert(
                ExpenseEntity(
                    cost = cost,
                    name = name
                )
            )
        }
    }
}