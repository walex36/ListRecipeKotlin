package com.example.listreceitasapp.domain.usecase

import com.example.listreceitasapp.domain.repository.RecipeRepository

class GetAllRecipesUseCase constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke() = repository.getAll()
}