package com.semorka.fundly.features.expense

import com.semorka.fundly.core.data.room.Category

data class ExpenseFormState(
    val expenseText: String = "",
    val scheduleText: String = "",
    val scheduleChecked: Boolean = false,
    val selectedCategory: Category? = null
)
