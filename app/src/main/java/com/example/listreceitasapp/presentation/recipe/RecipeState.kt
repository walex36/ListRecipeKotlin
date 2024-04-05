package com.example.listreceitasapp.presentation.recipe

import com.example.listreceitasapp.domain.model.RecipeDomain

sealed interface RecipeState {
    data object Loading : RecipeState
    data object Empty : RecipeState
    data class Success(val recipes: List<RecipeDomain>) : RecipeState
    data class Error(val message: String) : RecipeState
}