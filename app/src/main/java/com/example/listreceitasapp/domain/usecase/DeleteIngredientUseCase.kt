package com.example.listreceitasapp.domain.usecase

import com.example.listreceitasapp.domain.repository.RecipeRepository

class DeleteIngredientUseCase constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(id: Int) = repository.deleteIngredient(id)
}