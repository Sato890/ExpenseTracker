package com.sato890.expensetracker.data

import com.sato890.expensetracker.data.local.category.Category
import com.sato890.expensetracker.data.local.category.CategoryDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CategoryRepository @Inject constructor(
    private val categoryDao: CategoryDao
){

    suspend fun insert(category: Category) {
        categoryDao.insert(category)
    }
    suspend fun getAll(): Flow<List<Category>> {
        return categoryDao.getAll()
    }

    suspend fun delete(category: Category) {
        if (category.name != "No Category"){
            categoryDao.delete(category)
        }

    }
}