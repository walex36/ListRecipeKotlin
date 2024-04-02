package com.example.listreceitasapp.domain.repository

import com.example.listreceitasapp.domain.model.RecipeDomain

interface RecipeRepository {
    suspend fun getAll(): List<RecipeDomain>
    suspend fun insert(recipe: RecipeDomain)
}