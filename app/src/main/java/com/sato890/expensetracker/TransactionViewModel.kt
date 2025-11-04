package com.sato890.expensetracker

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.sato890.expensetracker.data.local.Transaction
import androidx.lifecycle.viewModelScope
import com.sato890.expensetracker.data.TransactionRepository
import kotlinx.coroutines.launch

data class TransactionScreenUiState(
    val description: String = "",
    val amount: String = "",
    val transactions: List<Transaction> = emptyList()
)

class TransactionViewModel(
    private val repository: TransactionRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(TransactionScreenUiState())

    val uiState: StateFlow<TransactionScreenUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllTransactions().collect { transactions ->
                _uiState.update { it.copy(transactions = transactions) }
            }
        }
    }

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

    fun addTransaction() {
        val description = _uiState.value.description
        val amount = _uiState.value.amount.toDoubleOrNull()

        if (description.isBlank() || amount == null) {
            return
        }

        val newTransaction = Transaction(description = description, amount = amount)

        viewModelScope.launch {
            repository.insert(newTransaction)
        }

        _uiState.update { it.copy(description = "", amount = "") }
    }
}