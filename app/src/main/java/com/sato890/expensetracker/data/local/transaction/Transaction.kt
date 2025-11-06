package com.sato890.expensetracker.data.local.transaction

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ForeignKey
import com.sato890.expensetracker.data.local.category.Category
@Entity(
    tableName = "transactions",
    foreignKeys = [
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.SET_NULL
        ),
        ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["accountId"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class Transaction(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val categoryId: Int?,
    val accountId: Int,
    val description: String,
    val amount: Double
)