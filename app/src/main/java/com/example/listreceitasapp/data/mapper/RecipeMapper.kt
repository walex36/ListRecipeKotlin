package com.example.listreceitasapp.data.mapper

import com.example.listreceitasapp.data.entity.RecipeEntity
import com.example.listreceitasapp.domain.model.RecipeDomain

fun RecipeDomain.toEntity() = RecipeEntity(
    id = id,
    name = name,
    prepareTime = prepareTime,
)

fun RecipeEntity.toDomain() = RecipeDomain(
    id = id,
    name = name,
    prepareTime = prepareTime,
)