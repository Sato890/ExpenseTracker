package com.sato890.expensetracker.data.local.category

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Insert
    suspend fun insert(category: Category)

    @Query("SELECT * FROM categories")
    fun getAll(): Flow<List<Category>>

    @Delete
    suspend fun delete(category: Category)
}