package com.example.listreceitasapp.domain.model

typealias FullRecipeDomain = FullRecipe

class FullRecipe(
    var recipe: RecipeDomain,
    var ingredient: List<IngredientDomain>,
    var prepareMode: List<PrepareModeDomain>
)