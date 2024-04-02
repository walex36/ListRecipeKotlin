package com.example.listreceitasapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.listreceitasapp.data.entity.RecipeEntity

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipe")
    fun getAll(): List<RecipeEntity>

    @Insert
    fun insert(recipe: RecipeEntity)
}