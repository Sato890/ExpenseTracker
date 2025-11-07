package com.sato890.expensetracker.ui.transaction.add


data class TransactionListItem(
    val id: Int,
    val description: String,
    val amount: Double,
    val categoryName: String,
    val date: Long = System.currentTimeMillis()
)