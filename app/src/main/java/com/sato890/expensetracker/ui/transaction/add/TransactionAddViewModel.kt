package com.sato890.expensetracker.ui.transaction.add

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.sato890.expensetracker.data.local.transaction.Transaction
import androidx.lifecycle.viewModelScope
import com.sato890.expensetracker.data.TransactionRepository
import com.sato890.expensetracker.data.local.account.Account
import com.sato890.expensetracker.data.local.category.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.Date



@HiltViewModel
class TransactionAddViewModel @Inject constructor(
    private val repository: TransactionRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(TransactionAddScreenUiState())

    val uiState: StateFlow<TransactionAddScreenUiState> = _uiState.asStateFlow()

    fun onDescriptionChange(newDescription: String) {
        _uiState.update { currentState ->
            currentState.copy(description = newDescription)
        }
    }

    fun onAmountChange(newAmount: String) {
        _uiState.update { currentState ->
            currentState.copy(amount = newAmount)
        }
    }

    fun onDateChange(newDateMillis: Long) {
        _uiState.update { it.copy(date = newDateMillis) }
    }

    fun addTransaction() {
        val description = _uiState.value.description
        val amount = _uiState.value.amount.toDoubleOrNull()

        if (description.isBlank() || amount == null) {
            return
        }

        val newTransaction = Transaction(
            description = description,
            amount = amount,
            categoryId = 1,
            accountId = 1,
            date = _uiState.value.date
        )

        viewModelScope.launch {
            repository.insert(newTransaction)
        }

        _uiState.update { it.copy(description = "", amount = "", date = System.currentTimeMillis() ) }
    }


}