package com.sato890.expensetracker.ui.transaction.add

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import com.sato890.expensetracker.data.local.transaction.Transaction
import androidx.lifecycle.viewModelScope
import com.sato890.expensetracker.data.AccountRepository
import com.sato890.expensetracker.data.CategoryRepository
import com.sato890.expensetracker.data.TransactionRepository
import com.sato890.expensetracker.data.local.account.Account
import com.sato890.expensetracker.data.local.category.Category
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import java.util.Date

@HiltViewModel
class TransactionAddViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val categoryRepository: CategoryRepository,
    private val accountRepository: AccountRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(TransactionAddScreenUiState())

    val uiState: StateFlow<TransactionAddScreenUiState> = _uiState.asStateFlow()

    init {
        observeData()
    }

    private fun observeData() {
        viewModelScope.launch {
            launch {
                accountRepository.getAll().collect { accounts ->
                    _uiState.update { state ->
                        state.copy(
                            accounts = accounts,
                            selectedAccountId = state.selectedAccountId ?: accounts.firstOrNull()?.id
                        )
                    }
                }
            }

            launch {
                categoryRepository.getAll().collect { categories ->
                    _uiState.update { state ->
                        state.copy(
                            categories = categories,
                            selectedCategoryId = state.selectedCategoryId ?: categories.firstOrNull()?.id
                        )
                    }
                }
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

    fun onDateChange(newDateMillis: Long) {
        _uiState.update { it.copy(date = newDateMillis) }
    }

    fun addTransaction() {
        val description = _uiState.value.description
        val amount = _uiState.value.amount.toDoubleOrNull()
        val selectedAccountId = _uiState.value.selectedAccountId
        val selectedCategoryId = _uiState.value.selectedCategoryId

        if (description.isBlank() || amount == null || selectedAccountId == null || selectedCategoryId == null) {
            return
        }

        val newTransaction = Transaction(
            description = description,
            amount = amount,
            categoryId = selectedCategoryId,
            accountId = selectedAccountId,
            date = _uiState.value.date
        )

        viewModelScope.launch {
            transactionRepository.insert(newTransaction)
        }

        _uiState.update {
            it.copy(
                description = "",
                amount = "",
                date = System.currentTimeMillis()
            )
        }
    }

    fun onAccountSelected(account: Account?) {
        _uiState.update {
            it.copy(selectedAccountId = account?.id)
        }
    }

    fun onCategorySelected(category: Category?) {
        _uiState.update {
            it.copy(selectedCategoryId = category?.id)
        }
    }
}