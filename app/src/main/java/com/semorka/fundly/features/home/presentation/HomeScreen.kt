package com.semorka.fundly.features.home.presentation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.semorka.fundly.core.data.DatastoreViewModel
import com.semorka.fundly.core.data.room.DatabaseViewModel
import com.semorka.fundly.core.data.room.ExpenseEntity
import com.semorka.fundly.ui.ScreenPreview

@Composable fun HomeScreen(){
    val dbViewModel: DatabaseViewModel = hiltViewModel()
    val dstViewModel: DatastoreViewModel = hiltViewModel()
    val expenses by dbViewModel.expenses.collectAsStateWithLifecycle()
    val funds by dstViewModel.userFundsFlow.collectAsStateWithLifecycle()

    HomeScreenContent(
        funds = funds,
        expenses = expenses
    )
}

@Composable
fun HomeScreenContent(
    funds: Int,
    expenses: List<ExpenseEntity>
) {
    Column {
        if (funds > 0) {
            Text("Your funds > 0")
        }
        expenses.forEach { expense ->
            Text(expense.cost.toString())
        }
    }
}

@Preview
@Composable
private fun HomeScreenPreview() {
    HomeScreenContent(
        funds = 100,
        expenses = emptyList()
    )
}