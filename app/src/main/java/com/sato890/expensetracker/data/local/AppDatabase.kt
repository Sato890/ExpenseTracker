package com.sato890.expensetracker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sato890.expensetracker.data.local.transaction.Transaction
import com.sato890.expensetracker.data.local.transaction.TransactionDao
import com.sato890.expensetracker.data.local.category.Category
import com.sato890.expensetracker.data.local.category.CategoryDao
import com.sato890.expensetracker.data.local.account.AccountDao
import com.sato890.expensetracker.data.local.account.Account

@Database(entities = [Transaction::class, Category::class, Account::class], version = 4)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
    abstract fun categoryDao(): CategoryDao

    abstract fun accountDao(): AccountDao

}