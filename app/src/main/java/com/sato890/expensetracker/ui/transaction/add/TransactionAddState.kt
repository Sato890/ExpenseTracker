package com.sato890.expensetracker.ui.transaction.add

import com.sato890.expensetracker.data.local.account.Account
import com.sato890.expensetracker.data.local.category.Category
import java.util.Date


data class TransactionAddScreenUiState(
    val description: String = "",
    val amount: String = "",
    val date: Long = Date().time,
    val selectedAccountId: Int? = null,
    val selectedCategoryId: Int? = null,
    val accounts: List<Account> = emptyList(),
    val categories: List<Category> = emptyList()
)
