package com.sato890.expensetracker.data.local.account

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AccountDao {
    @Insert
    suspend fun insert(account: Account)

    @Query("SELECT * FROM accounts ORDER BY name ASC")
    fun getAll(): Flow<List<Account>>
}