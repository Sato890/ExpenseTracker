package com.sato890.expensetracker.ui.transaction.add

import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.sato890.expensetracker.util.formatDate
import java.util.Calendar
import kotlin.math.sin

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TransactionAddScreen(
    onNavigateUp: () -> Unit,
    viewModel: TransactionAddViewModel = hiltViewModel()
) {

    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val context = LocalContext.current
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = uiState.date

    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val newDate = Calendar.getInstance().apply {
                set(year, month, dayOfMonth)
            }
            viewModel.onDateChange(newDate.timeInMillis)
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Add New Transaction", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = uiState.description,
            onValueChange = { viewModel.onDescriptionChange(it) },
            label = { Text("Description") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = uiState.amount,
            onValueChange = { viewModel.onAmountChange(it) },
            label = { Text("Amount") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )

        Spacer(modifier = Modifier.height(8.dp))

        var expanded by remember { mutableStateOf(false) }

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                modifier = Modifier.menuAnchor().fillMaxWidth(),
                readOnly = true,
                value = uiState.accounts.find { it.id == uiState.selectedAccountId }?.name ?: "",
                onValueChange = { },
                label = { Text("Account") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                uiState.accounts.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption.name) },
                        onClick = {
                            viewModel.onAccountSelected(selectionOption)
                            expanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        var categoryExpanded by remember { mutableStateOf(false) }

        ExposedDropdownMenuBox(
            expanded = categoryExpanded,
            onExpandedChange = { categoryExpanded = !categoryExpanded },
            modifier = Modifier.fillMaxWidth()
        ) {
            TextField(
                modifier = Modifier.menuAnchor().fillMaxWidth(),
                readOnly = true,
                value = uiState.categories.find { it.id == uiState.selectedCategoryId }?.name
                    ?: "",
                onValueChange = { },
                label = { Text("Category") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = categoryExpanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
            )
            ExposedDropdownMenu(
                expanded = categoryExpanded,
                onDismissRequest = { categoryExpanded = false },
            ) {
                uiState.categories.forEach { selectionOption ->
                    DropdownMenuItem(
                        text = { Text(selectionOption.name) },
                        onClick = {
                            viewModel.onCategorySelected(selectionOption)
                            categoryExpanded = false
                        },
                        contentPadding = ExposedDropdownMenuDefaults.ItemContentPadding,
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { datePickerDialog.show() }
                .padding(vertical = 16.dp)
        ) {
            Text(text = "Date: ${formatDate(uiState.date)}")
        }

        Button(
            onClick = {
                viewModel.addTransaction()
                onNavigateUp()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("ADD TRANSACTION")
        }
    }
}
