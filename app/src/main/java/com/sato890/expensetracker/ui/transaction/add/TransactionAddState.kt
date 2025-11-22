package com.sato890.expensetracker.ui.transaction.add

import java.util.Date


data class TransactionAddScreenUiState(
    val description: String = "",
    val amount: String = "",
    val date: Long = Date().time
)