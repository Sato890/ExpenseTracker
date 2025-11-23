package com.sato890.expensetracker.data

import com.sato890.expensetracker.data.local.transaction.Transaction
import com.sato890.expensetracker.data.local.transaction.TransactionDao
import com.sato890.expensetracker.data.local.category.Category
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class TransactionRepository @Inject constructor(
    private val transactionDao: TransactionDao
) {
    suspend fun getFeedItemsForAccount(accountId: Int): Flow<Map<Category, List<Transaction>>> {
        return transactionDao.getTransactionFeedItemsForAccounts(accountId)
    }

    suspend fun insert(transaction: Transaction) {
        transactionDao.insert(transaction)
    }

}

