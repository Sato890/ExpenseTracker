package com.sato890.expensetracker.data

import com.sato890.expensetracker.data.local.account.Account
import com.sato890.expensetracker.data.local.account.AccountDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AccountRepository @Inject constructor(
    private val accountDao: AccountDao
) {
    fun getAllAccounts(): Flow<List<Account>> {
        return accountDao.getAll()
    }

    suspend fun insertAccount(account: Account) {
        accountDao.insert(account)
    }
}
