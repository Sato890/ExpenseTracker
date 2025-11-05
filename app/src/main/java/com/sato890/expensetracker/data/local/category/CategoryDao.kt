package com.sato890.expensetracker.data.local.category

import androidx.room.Dao
import androidx.room.Insert

@Dao
interface CategoryDao {
    @Insert
    suspend fun insert(category: Category)
}