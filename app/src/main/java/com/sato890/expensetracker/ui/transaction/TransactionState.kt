package com.sato890.expensetracker.ui.transaction


data class TransactionListItem(
    val id: Int,
    val description: String,
    val amount: Double,
    val categoryName: String,
)