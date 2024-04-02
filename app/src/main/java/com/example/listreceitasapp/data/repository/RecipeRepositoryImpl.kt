package com.example.listreceitasapp.data.repository

import com.example.listreceitasapp.data.dao.RecipeDao
import com.example.listreceitasapp.data.mapper.toDomain
import com.example.listreceitasapp.data.mapper.toEntity
import com.example.listreceitasapp.domain.model.RecipeDomain
import com.example.listreceitasapp.domain.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipeRepositoryImpl(private val dao: RecipeDao) : RecipeRepository {
    override suspend fun getAll(): List<RecipeDomain> =
        withContext(Dispatchers.IO) {
            dao.getAll().map {
                it.toDomain()
            }
        }


    override suspend fun insert(recipe: RecipeDomain) = withContext(Dispatchers.IO) {
        dao.insert(recipe.toEntity())
    }
}