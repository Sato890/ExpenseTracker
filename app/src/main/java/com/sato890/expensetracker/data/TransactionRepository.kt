package com.sato890.expensetracker.data

import com.sato890.expensetracker.data.local.Transaction
import com.sato890.expensetracker.data.local.TransactionDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionRepository @Inject constructor(
    private val transactionDao: TransactionDao
) {
    fun getAllTransactions(): Flow<List<Transaction>> {
        return transactionDao.getAll()
    }

    suspend fun insert(transaction: Transaction) {
        transactionDao.insert(transaction)
    }
}