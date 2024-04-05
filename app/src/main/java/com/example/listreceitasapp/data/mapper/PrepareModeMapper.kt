package com.example.listreceitasapp.data.mapper

import com.example.listreceitasapp.data.entity.PrepareModeEntity
import com.example.listreceitasapp.domain.model.PrepareModeDomain

fun PrepareModeDomain.toEntity() = PrepareModeEntity(
    id = id,
    name = name,
    idRecipe = idRecipe
)

fun PrepareModeEntity.toDomain() = PrepareModeDomain(
    id = id,
    name = name,
    idRecipe = idRecipe
)