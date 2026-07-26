package com.semorka.fundly.core.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Query("SELECT * FROM ExpenseEntity")
    fun getAll(): Flow<List<ExpenseEntity>>
    @Insert
    suspend fun insert(expense: ExpenseEntity)
}