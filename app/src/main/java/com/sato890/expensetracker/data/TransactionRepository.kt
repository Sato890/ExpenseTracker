package com.sato890.expensetracker.data

import com.sato890.expensetracker.data.local.account.Account
import com.sato890.expensetracker.data.local.account.AccountDao
import com.sato890.expensetracker.data.local.transaction.Transaction
import com.sato890.expensetracker.data.local.transaction.TransactionDao
import com.sato890.expensetracker.data.local.category.Category
import com.sato890.expensetracker.data.local.category.CategoryDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TransactionRepository @Inject constructor(
    private val transactionDao: TransactionDao,
    private val categoryDao: CategoryDao,
    private val accountDao: AccountDao
) {
    fun getAllTransactions(): Flow<List<Transaction>> {
        return transactionDao.getAll()
    }

    fun getTransactionFeedItems(): Flow<Map<Category, List<Transaction>>> {
        return transactionDao.getTransactionFeedItems()
    }

    fun getAllAccounts(): Flow<List<Account>> {
        return accountDao.getAll()
    }

    suspend fun insert(transaction: Transaction) {
        transactionDao.insert(transaction)
    }

    suspend fun insertCategory(category: Category) {
        categoryDao.insert(category)
    }

    suspend fun insertAccount(account: Account) {
        accountDao.insert(account)
    }
}
