package com.sato890.expensetracker

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sato890.expensetracker.ui.theme.ExpenseTrackerTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.sato890.expensetracker.data.TransactionRepository

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = (application as ExpenseTrackerApp).database
        val dao = database.transactionDao()
        val repository = TransactionRepository(dao)
        val viewModelFactory = TransactionViewModelFactory(repository)

        enableEdgeToEdge()
        setContent {
            ExpenseTrackerTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TransactionScreen(factory = viewModelFactory)
                }
            }
        }
    }
}
