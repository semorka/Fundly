package com.semorka.fundly.core.data.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ExpenseEntity (
    @PrimaryKey(autoGenerate = true) val uid: Int = 0,
    val cost: Double,
    val name: String,
    val timestamp: Long = System.currentTimeMillis(),
    val schedule : Int? = null
)