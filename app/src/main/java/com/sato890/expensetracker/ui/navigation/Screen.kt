package com.sato890.expensetracker.ui.navigation

sealed class Screen(val route: String) {
    data object Dashboard : Screen("dashboard")
    data object Budgets : Screen("budgets")
    data object TransactionList: Screen("transaction/list")

    data object TransactionAdd : Screen("transaction/add")
}