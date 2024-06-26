package com.example.listreceitasapp.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

typealias RecipeEntity = Recipe

@Entity
data class Recipe (
    @PrimaryKey(true) val id : Int,
    @ColumnInfo(name = "name") val name : String,
    @ColumnInfo(name = "prepareTime") val prepareTime: String,
)