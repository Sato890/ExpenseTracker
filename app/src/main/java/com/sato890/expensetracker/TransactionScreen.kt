package com.sato890.expensetracker

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.Divider
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items


@Composable
fun TransactionScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Add New Transaction", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = "",
            onValueChange = { },
            label = { Text("Amount") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("ADD TRANSACTION")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Divider() // A visual separator line

        Spacer(modifier = Modifier.height(16.dp))

        val transactions = listOf("Groceries: $55.40", "Gas: $40.00", "Coffee: $4.50", "Rent: $1200.00", "Movie Tickets: $25.00","Groceries: $55.40", "Gas: $40.00", "Coffee: $4.50", "Rent: $1200.00", "Movie Tickets: $25.00","Groceries: $55.40", "Gas: $40.00", "Coffee: $4.50", "Rent: $1200.00", "Movie Tickets: $25.00","Groceries: $55.40", "Gas: $40.00", "Coffee: $4.50", "Rent: $1200.00", "Movie Tickets: $25.00","Groceries: $55.40", "Gas: $40.00", "Coffee: $4.50", "Rent: $1200.00", "Movie Tickets: $25.00")

        LazyColumn {
            items(transactions) { transaction ->
                Text(text = transaction, modifier = Modifier.padding(vertical = 8.dp))
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
fun TransactionScreenPreview() {
    TransactionScreen()
}