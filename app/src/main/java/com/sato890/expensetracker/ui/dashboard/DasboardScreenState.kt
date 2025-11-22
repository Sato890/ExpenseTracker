package com.sato890.expensetracker.ui.dashboard

import com.sato890.expensetracker.data.local.account.Account
import com.sato890.expensetracker.ui.transaction.list.TransactionListUiState

data class DashboardUiState(
    val accounts: List<Account> = emptyList(),
    val recentTransactions: List<TransactionListUiState> = emptyList()
)

