package com.sato890.expensetracker.ui.dashboard


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sato890.expensetracker.data.AccountRepository
import com.sato890.expensetracker.data.TransactionRepository
import com.sato890.expensetracker.data.local.account.Account
import com.sato890.expensetracker.ui.transaction.add.TransactionListItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

data class DashboardUiState(
    val accounts: List<Account> = emptyList(),
    val recentTransactions: List<TransactionListItem> = emptyList()
)

@OptIn(ExperimentalCoroutinesApi::class)
@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val accountRepository: AccountRepository
) : ViewModel() {

    private val _selectedAccountId = MutableStateFlow<Int?>(null)

    val uiState: StateFlow<DashboardUiState>

    init {
        val accountsFlow = accountRepository.getAllAccounts()

        val transactionsFlow = _selectedAccountId.flatMapLatest { accountId ->
            if (accountId == null) {
                flowOf(emptyList())
            } else {

                transactionRepository.getTransactionFeedItemsForAccount(accountId)
                    .map { transactionMap ->
                        transactionMap.flatMap { (category, transactions) ->
                            transactions.map { transaction ->
                                TransactionListItem(
                                    id = transaction.id,
                                    description = transaction.description,
                                    amount = transaction.amount,
                                    categoryName = category.name,
                                    date = transaction.date
                                )
                            }
                        }.sortedByDescending { it.date }
                    }
            }
        }

        uiState = combine(accountsFlow, transactionsFlow) { accounts, transactions ->
            if (_selectedAccountId.value == null && accounts.isNotEmpty()) {
                _selectedAccountId.value = accounts.first().id
            }

            DashboardUiState(
                accounts = accounts,
                recentTransactions = transactions
            )
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = DashboardUiState()
        )
    }

    fun onAccountSelected(account: Account) {
        _selectedAccountId.value = account.id
    }
}