package com.sato890.expensetracker.ui.transaction

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.sato890.expensetracker.data.local.transaction.Transaction
import androidx.lifecycle.viewModelScope
import com.sato890.expensetracker.data.TransactionRepository
import com.sato890.expensetracker.data.local.category.Category
import kotlinx.coroutines.launch
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import com.sato890.expensetracker.ui.transaction.TransactionListItem
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

data class TransactionScreenUiState(
    val description: String = "",
    val amount: String = "",
    val transactions: List<TransactionListItem> = emptyList()
)

@HiltViewModel
class TransactionViewModel @Inject constructor(
    private val repository: TransactionRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(TransactionScreenUiState())

    val uiState: StateFlow<TransactionScreenUiState> = _uiState.asStateFlow()

    init {
        seedCategories()

        viewModelScope.launch {
            repository.getTransactionFeedItems()
                .map { transactionMap ->
                    transactionMap.flatMap { (category, transactions) ->
                        transactions.map { transaction ->
                            TransactionListItem(
                                id = transaction.id,
                                description = transaction.description,
                                amount = transaction.amount,
                                categoryName = category.name
                            )
                        }
                    }
                }
                .collect { transactions ->
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

        val newTransaction = Transaction(
            description = description,
            amount = amount,
            categoryId = 1
        )

        viewModelScope.launch {
            repository.insert(newTransaction)
        }

        _uiState.update { it.copy(description = "", amount = "") }
    }

    private fun seedCategories() {
        viewModelScope.launch {
            if (repository.getTransactionFeedItems().first().isEmpty()) {
                repository.insertCategory(Category(name = "Food"))
                repository.insertCategory(Category(name = "Transport"))
                repository.insertCategory(Category(name = "Bills"))
            }
        }
    }
}