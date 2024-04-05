package com.example.listreceitasapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.listreceitasapp.data.entity.FullRecipeEntity
import com.example.listreceitasapp.data.entity.Ingredient
import com.example.listreceitasapp.data.entity.PrepareMode
import com.example.listreceitasapp.data.entity.RecipeEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipe")
    fun getAll(): Flow<List<RecipeEntity>>

    @Insert
    fun insert(recipe: RecipeEntity)

    @Insert
    fun insert(ingredient: Ingredient)

    @Insert
    fun insert(prepareMode: PrepareMode)

    @Transaction
    @Query("SELECT * FROM recipe WHERE id = :recipeId")
    fun getRecipeWithIngredientsAndPrepareMode(recipeId: Int):Flow<FullRecipeEntity>

    @Update
    fun updateIngredient(ingredient: Ingredient)

    @Update
    fun updatePrepareMode(prepareMode: PrepareMode)

    @Query("DELETE FROM ingredient WHERE id = :id")
    fun deleteIngredient(id: Int)

    @Query("DELETE FROM prepareMode WHERE id = :id")
    fun deletePreparedMode(id: Int)
}