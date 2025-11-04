package com.sato890.expensetracker
import android.app.Application
import androidx.room.Room
import com.sato890.expensetracker.data.local.AppDatabase

class ExpenseTrackerApp : Application() {

    lateinit var database: AppDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "expense_tracker_db"
        ).build()
    }
}