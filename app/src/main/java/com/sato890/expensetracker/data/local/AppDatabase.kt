package com.sato890.expensetracker.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sato890.expensetracker.data.local.transaction.Transaction
import com.sato890.expensetracker.data.local.transaction.TransactionDao
import com.sato890.expensetracker.data.local.category.Category
import com.sato890.expensetracker.data.local.category.CategoryDao

@Database(entities = [Transaction::class, Category::class], version = 2)
abstract class AppDatabase : RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
    abstract fun categoryDao(): CategoryDao
}