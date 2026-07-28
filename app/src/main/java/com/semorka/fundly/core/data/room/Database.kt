package com.semorka.fundly.core.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [ExpenseEntity::class], version = 4)
abstract class AppDatabase: RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
}