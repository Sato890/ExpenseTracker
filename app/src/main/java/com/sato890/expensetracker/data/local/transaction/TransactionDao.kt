package com.sato890.expensetracker.data.local.transaction
import com.sato890.expensetracker.data.local.category.Category

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {

    @Insert
    suspend fun insert(transaction: Transaction)

    @Query("SELECT * FROM transactions")
    fun getAll(): Flow<List<Transaction>>

    @Query("""
        SELECT * FROM transactions
        INNER JOIN categories ON transactions.categoryId = categories.id
    """)
    fun getTransactionFeedItems(): Flow<Map<Category, List<Transaction>>>

    @Query("""
        SELECT * FROM transactions 
        INNER JOIN categories ON transactions.categoryId = categories.id
        WHERE transactions.accountId = :accountId
    """
    )
    fun getTransactionFeedItemsForAccounts(accountId: Int): Flow<Map<Category, List<Transaction>>>
}