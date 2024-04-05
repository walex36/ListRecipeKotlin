package com.example.listreceitasapp.domain.usecase

import com.example.listreceitasapp.domain.model.PrepareModeDomain
import com.example.listreceitasapp.domain.repository.RecipeRepository

class InsertPrepareModeUseCase constructor(
    private val repository: RecipeRepository
) {
    suspend operator fun invoke(prepareMode: PrepareModeDomain) = repository.insert(prepareMode)
}