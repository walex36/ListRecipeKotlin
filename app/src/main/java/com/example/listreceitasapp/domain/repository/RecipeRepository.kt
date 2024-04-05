package com.example.listreceitasapp.domain.repository

import com.example.listreceitasapp.domain.model.FullRecipe
import com.example.listreceitasapp.domain.model.IngredientDomain
import com.example.listreceitasapp.domain.model.PrepareModeDomain
import com.example.listreceitasapp.domain.model.RecipeDomain
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {
    suspend fun getAll(): Flow<List<RecipeDomain>>
    suspend fun insert(recipe: RecipeDomain)
    suspend fun insert(prepareMode: PrepareModeDomain)
    suspend fun inset(ingredient: IngredientDomain)
    suspend fun getRecipeWithIngredientsAndPrepareMode(idRecipe: Int): Flow<FullRecipe>
    suspend fun update(ingredient: IngredientDomain)
    suspend fun update(prepareMode: PrepareModeDomain)
    suspend fun deleteIngredient(id: Int)
    suspend fun deletePrepareMode(id: Int)
}
