package com.example.listreceitasapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.listreceitasapp.data.dao.RecipeDao
import com.example.listreceitasapp.data.entity.FullRecipeEntity
import com.example.listreceitasapp.data.entity.Ingredient
import com.example.listreceitasapp.data.entity.IngredientEntity
import com.example.listreceitasapp.data.entity.PrepareMode
import com.example.listreceitasapp.data.entity.PrepareModeEntity
import com.example.listreceitasapp.data.entity.Recipe
import com.example.listreceitasapp.data.entity.RecipeEntity

@Database(
    entities = [Recipe::class,
        Ingredient::class,
        PrepareMode::class], version = 2
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}