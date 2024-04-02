package com.example.listreceitasapp.data.entity

import androidx.room.Embedded
import androidx.room.Relation

data class FullRecipe(
    @Embedded val recipe: RecipeEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "idRecipe"
    )
    val ingredient: List<Ingredient>,
    @Relation(
        parentColumn = "id",
        entityColumn = "idRecipe"
    )
    val prepareMode: List<PrepareMode>,
)
