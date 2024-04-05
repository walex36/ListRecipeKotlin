package com.example.listreceitasapp.domain.usecase

import com.example.listreceitasapp.domain.repository.RecipeRepository

class GetRecipeWithIngredientAndPrepareModeUseCase constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(idRecipe: Int) = repository.getRecipeWithIngredientsAndPrepareMode(idRecipe)
}