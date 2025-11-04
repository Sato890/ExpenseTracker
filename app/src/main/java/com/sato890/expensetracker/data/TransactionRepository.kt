package com.sato890.expensetracker.data

import com.sato890.expensetracker.data.local.Transaction
import com.sato890.expensetracker.data.local.TransactionDao
import kotlinx.coroutines.flow.Flow

class TransactionRepository(
    private val transactionDao: TransactionDao
) {
    fun getAllTransactions(): Flow<List<Transaction>> {
        return transactionDao.getAll()
    }

    suspend fun insert(transaction: Transaction) {
        transactionDao.insert(transaction)
    }
}