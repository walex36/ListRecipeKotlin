package com.example.listreceitasapp.domain.model

typealias IngredientDomain = Ingredient

class Ingredient (
    val id: Int = 0,
    val name: String,
    val idRecipe: Int
)