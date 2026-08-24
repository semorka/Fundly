package com.semorka.fundly.core.data.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.math.floor
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.DurationUnit

@HiltViewModel
class DatabaseViewModel @Inject constructor(
    private val dao: ExpenseDao
) : ViewModel() {
    val oneTimeExpenses: StateFlow<List<ExpenseEntity>> = dao.getOneTimeExpenses()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    val scheduledExpenses: StateFlow<List<ExpenseEntity>> = dao.getScheduledExpenses()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = emptyList()
        )
    val totalExpenses: StateFlow<Double> = combine(
        oneTimeExpenses,
        scheduledExpenses
    ) { oneTimeList, scheduledList ->
        val oneTimeSum = oneTimeList.sumOf { it.cost }
        val scheduledSum = scheduledList.sumOf { getScheduledAmount(it) }
        oneTimeSum + scheduledSum
    }
        .flowOn(Dispatchers.Default)
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = 0.0
        )

    fun addExpense(cost: Double, name: String, schedule: Int? = null, category: Category?) {
        viewModelScope.launch {
            dao.insert(
                ExpenseEntity(
                    cost = cost,
                    name = name,
                    schedule = schedule,
                    category = category
                )
            )
        }
    }
    fun getScheduledAmount(expenseEntity: ExpenseEntity): Double {
        val schedule = expenseEntity.schedule ?: return expenseEntity.cost

        val currentDate = System.currentTimeMillis().milliseconds.toDouble(DurationUnit.DAYS)
        val firstExpenseDate = expenseEntity.timestamp.milliseconds.toDouble(DurationUnit.DAYS)
        val interval = schedule.toDouble()

        if (interval <= 0) return expenseEntity.cost

        return (floor((currentDate - firstExpenseDate) / interval) + 1) * expenseEntity.cost
    }
}
