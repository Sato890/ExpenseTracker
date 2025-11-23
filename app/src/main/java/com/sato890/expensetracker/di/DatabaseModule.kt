package com.sato890.expensetracker.di

import android.content.Context
import androidx.room.Room
import com.sato890.expensetracker.data.local.AppDatabase
import com.sato890.expensetracker.data.local.category.CategoryDao
import com.sato890.expensetracker.data.local.transaction.TransactionDao
import com.sato890.expensetracker.data.local.account.AccountDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton
import android.content.ContentValues
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.RoomDatabase
import com.sato890.expensetracker.data.local.category.CategoryDefaults
import android.database.sqlite.SQLiteDatabase



@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "expense_tracker_db"
        )
            .fallbackToDestructiveMigration(false)
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)

                    CategoryDefaults.defaultCategories.forEach { category ->
                        val values = ContentValues().apply {
                            put("name", category.name)
                            put("icon", category.icon)
                        }

                        db.insert("categories", SQLiteDatabase.CONFLICT_REPLACE, values)
                    }
                }
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideTransactionDao(database: AppDatabase): TransactionDao {
        return database.transactionDao()
    }

    @Provides
    @Singleton
    fun provideCategoryDao(database: AppDatabase): CategoryDao {
        return database.categoryDao()
    }

    @Provides
    @Singleton
    fun provideAccountDao(database: AppDatabase): AccountDao {
        return database.accountDao()
    }

}
