package com.sato890.expensetracker.ui.transaction.list

data class TransactionListUiState(
    val id: Int,
    val description: String,
    val amount: Double,
    val categoryName: String,
    val date: Long = System.currentTimeMillis()
)