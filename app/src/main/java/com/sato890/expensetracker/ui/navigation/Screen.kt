package com.sato890.expensetracker.ui.navigation

sealed class Screen(val route: String) {
    data object Dashboard : Screen("dashboard")
    data object AddTransaction : Screen("add_transaction")
}