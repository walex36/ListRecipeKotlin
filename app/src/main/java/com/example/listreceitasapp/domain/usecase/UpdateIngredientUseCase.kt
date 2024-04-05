package com.example.listreceitasapp.domain.usecase

import com.example.listreceitasapp.domain.model.IngredientDomain
import com.example.listreceitasapp.domain.repository.RecipeRepository

class UpdateIngredientUseCase constructor(
    private val repository: RecipeRepository,
) {
    suspend operator fun invoke(ingredient: IngredientDomain) = repository.update(ingredient)
}