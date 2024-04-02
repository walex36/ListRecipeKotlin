package com.example.listreceitasapp.domain.usecase

import com.example.listreceitasapp.domain.model.RecipeDomain
import com.example.listreceitasapp.domain.repository.RecipeRepository

class InsertRecipeUseCase constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(recipe: RecipeDomain) = repository.insert(recipe)
}