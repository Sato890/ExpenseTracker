package com.sato890.expensetracker.ui.account.detail

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import androidx.lifecycle.viewModelScope
import com.sato890.expensetracker.data.AccountRepository
import com.sato890.expensetracker.data.local.account.Account
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class AccountDetailViewModel @Inject constructor (
    private val repository: AccountRepository
): ViewModel(){
    private val _uiState = MutableStateFlow(AccountDetailScreenUiState())
    val uiState: StateFlow<AccountDetailScreenUiState> = _uiState.asStateFlow()


    fun onNameChange(newName: String) {
        _uiState.update { currentState ->
            currentState.copy(name = newName)
        }
    }

    fun saveAccount() {
        val name = _uiState.value.name

        val newAccount = Account(
            name = name,
            currency = "EUR"
        )

        viewModelScope.launch {
            repository.insert(newAccount)
        }

        _uiState.update { it.copy(name = "") }
    }
}
