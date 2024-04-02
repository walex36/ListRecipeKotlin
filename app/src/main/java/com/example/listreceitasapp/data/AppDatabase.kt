package com.example.listreceitasapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.listreceitasapp.data.dao.RecipeDao
import com.example.listreceitasapp.data.entity.RecipeEntity

@Database(entities = [RecipeEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}