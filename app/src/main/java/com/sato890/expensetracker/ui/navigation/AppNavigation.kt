package com.sato890.expensetracker.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sato890.expensetracker.ui.transaction.AddTransactionScreen
import com.sato890.expensetracker.ui.dashboard.DashboardScreen


@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Screen.Dashboard.route
    ) {
        composable(Screen.Dashboard.route) {
            DashboardScreen(onAddTransactionClick = {
                navController.navigate(Screen.AddTransaction.route)
            })
        }
        composable(Screen.AddTransaction.route) {
            AddTransactionScreen(onNavigateUp = {
                navController.navigateUp()
            })
        }
    }
}
