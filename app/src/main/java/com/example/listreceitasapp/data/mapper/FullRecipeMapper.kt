package com.example.listreceitasapp.data.mapper

import com.example.listreceitasapp.data.entity.FullRecipeEntity
import com.example.listreceitasapp.domain.model.FullRecipeDomain

fun FullRecipeDomain.toEntity() = FullRecipeEntity(
    recipe = recipe.toEntity(),
    ingredient = ingredient.map {
        it.toEntity()
    },
    prepareMode = prepareMode.map {
        it.toEntity()
    }
)

fun FullRecipeEntity.toDomain() = FullRecipeDomain(
    recipe = recipe.toDomain(),
    ingredient = ingredient.map {
        it.toDomain()
    },
    prepareMode = prepareMode.map {
        it.toDomain()
    }
)