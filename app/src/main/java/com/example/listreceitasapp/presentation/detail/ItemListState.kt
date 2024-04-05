package com.example.listreceitasapp.presentation.detail

import com.example.listreceitasapp.domain.model.IngredientDomain
import com.example.listreceitasapp.domain.model.PrepareModeDomain

interface ItemListState {
    object Loading : ItemListState
    data class Success(
        val ingredients: List<IngredientDomain>,
        val prepareMode: List<PrepareModeDomain>
    ) : ItemListState
    data class Error(val message: String) : ItemListState
}