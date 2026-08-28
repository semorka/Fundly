package com.semorka.fundly.features.expense.domain

import com.semorka.fundly.core.data.room.Category

data class ExpenseFormState(
    val expenseName: String = "",
    val expenseText: String = "",
    val scheduleText: String = "",
    val scheduleChecked: Boolean = false,
    val selectedCategory: Category? = null
)
