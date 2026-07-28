package com.semorka.fundly.core.features.home.presentation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.semorka.fundly.core.data.DatastoreViewModel
import com.semorka.fundly.core.data.room.DatabaseViewModel

@Composable fun HomeScreen(
    dbViewModel : DatabaseViewModel,
    dstViewModel : DatastoreViewModel
){
    val funds by dstViewModel.userFundsFlow.collectAsStateWithLifecycle()
    val oneTimeExpenses by dbViewModel.oneTimeExpenses.collectAsStateWithLifecycle()
    val scheduledExpenses by dbViewModel.scheduledExpenses.collectAsStateWithLifecycle()
    val totalExpenses by dbViewModel.totalExpenses.collectAsStateWithLifecycle()

    HomeContent(
        funds = funds,
        oneTimeExpenses = oneTimeExpenses,
        scheduledExpenses = scheduledExpenses,
        getScheduledAmount = { expenseEntity ->
            dbViewModel.getScheduledAmount(expenseEntity)
        },
        totalExpenses = totalExpenses
    )
}