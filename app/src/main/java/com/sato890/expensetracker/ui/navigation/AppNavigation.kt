package com.sato890.expensetracker.ui.navigation

import androidx.compose.material3.Scaffold
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sato890.expensetracker.ui.transaction.add.TransactionAddScreen
import com.sato890.expensetracker.ui.dashboard.DashboardScreen
import com.sato890.expensetracker.ui.transaction.list.TransactionListScreen
import com.sato890.expensetracker.ui.budgets.BudgetsScreen


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { /* TODO */ },
        floatingActionButton = {/* TODO */ }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Dashboard.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(Screen.Dashboard.route) {
                DashboardScreen(onAddTransactionClick = {
                    navController.navigate(Screen.TransactionAdd.route)
                })
            }
            composable(Screen.TransactionList.route) { TransactionListScreen() }
            composable(Screen.Budgets.route) { BudgetsScreen() }

            composable(Screen.TransactionAdd.route) {
                TransactionAddScreen(onNavigateUp = { navController.navigateUp() })
            }
        }
    }
}

