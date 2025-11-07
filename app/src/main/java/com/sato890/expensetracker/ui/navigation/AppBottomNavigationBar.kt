package com.sato890.expensetracker.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBalance
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.PieChart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun AppBottomNavigationBar(navController: NavController) {
    // A list of our top-level screens that should appear on the bottom bar.
    val items = listOf(
        Screen.Dashboard to Icons.Default.AccountBalance,
        Screen.TransactionList to Icons.Default.List,
        Screen.Budgets to Icons.Default.PieChart
    )

    NavigationBar {
        // This gets the current navigation destination as a state object.
        // When the user navigates, this state updates, and the UI recomposes.
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        items.forEach { (screen, icon) ->
            NavigationBarItem(
                icon = { Icon(icon, contentDescription = screen.route) },
                label = { Text(screen.route.substringBefore('/').replaceFirstChar { it.uppercase() }) },

                // The item is 'selected' if its route matches the current route.
                selected = currentRoute == screen.route,

                onClick = {
                    // Navigate to the screen's route when the item is clicked.
                    navController.navigate(screen.route) {
                        // This is a crucial piece of navigation logic.
                        // It pops all destinations up to the start destination of the graph
                        // from the back stack, which prevents a large stack of destinations
                        // from building up as users switch tabs.
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        // Avoids re-launching the same destination if it's already on top.
                        launchSingleTop = true
                        // Restores state when re-selecting a previously selected item.
                        restoreState = true
                    }
                }
            )
        }
    }
}