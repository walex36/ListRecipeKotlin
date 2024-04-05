package com.example.listreceitasapp.data.mapper

import com.example.listreceitasapp.data.entity.IngredientEntity
import com.example.listreceitasapp.domain.model.IngredientDomain

fun IngredientDomain.toEntity() = IngredientEntity(
    id = id,
    name = name,
    idRecipe = idRecipe
)

fun IngredientEntity.toDomain() = IngredientDomain(
    id = id,
    name = name,
    idRecipe = idRecipe
)