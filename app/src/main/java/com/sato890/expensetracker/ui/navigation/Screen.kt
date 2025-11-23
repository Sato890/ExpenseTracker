package com.sato890.expensetracker.ui.navigation

sealed class Screen(val route: String) {
    data object Dashboard : Screen("dashboard")
    data object Budgets : Screen("budgets")
    data object TransactionList: Screen("transaction/list")

    object TransactionAdd : Screen("transaction/add")

    data object AccountDetail : Screen("account/detail")

}