package com.semorka.fundly.core.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM ExpenseEntity ORDER BY timestamp DESC")
    fun getAll(): Flow<List<ExpenseEntity>>

    @Query("SELECT * FROM ExpenseEntity WHERE schedule IS NULL")
    fun getOneTimeExpenses(): Flow<List<ExpenseEntity>>

    @Query("SELECT * FROM ExpenseEntity WHERE schedule IS NOT NULL")
    fun getScheduledExpenses(): Flow<List<ExpenseEntity>>

    @Insert
    suspend fun insert(expense: ExpenseEntity)
}