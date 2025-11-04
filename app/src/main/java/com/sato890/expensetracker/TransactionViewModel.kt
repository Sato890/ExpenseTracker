package com.sato890.expensetracker

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

data class TransactionScreenUiState(
    val description: String = "",
    val amount: String = "",
    val transactions: List<String> = emptyList()
)

class TransactionViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(TransactionScreenUiState())

    val uiState: StateFlow<TransactionScreenUiState> = _uiState.asStateFlow()

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
        val amount = _uiState.value.amount

        if (description.isBlank() || amount.isBlank()) {
            return
        }

        val newTransaction = "$description: $$amount"

        _uiState.update { currentState ->
            currentState.copy(
                transactions = currentState.transactions + newTransaction,
                description = "",
                amount = ""
            )
        }
    }
}