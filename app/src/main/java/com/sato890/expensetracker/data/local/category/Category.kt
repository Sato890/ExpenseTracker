package com.sato890.expensetracker.data.local.category

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class Category(
    val name: String,
    val icon: String,
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
)