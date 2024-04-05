package com.example.listreceitasapp.data.repository

import com.example.listreceitasapp.data.dao.RecipeDao
import com.example.listreceitasapp.data.mapper.toDomain
import com.example.listreceitasapp.data.mapper.toEntity
import com.example.listreceitasapp.domain.model.FullRecipeDomain
import com.example.listreceitasapp.domain.model.IngredientDomain
import com.example.listreceitasapp.domain.model.PrepareModeDomain
import com.example.listreceitasapp.domain.model.RecipeDomain
import com.example.listreceitasapp.domain.repository.RecipeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class RecipeRepositoryImpl(private val dao: RecipeDao) : RecipeRepository {
    override suspend fun getAll(): Flow<List<RecipeDomain>> = withContext(Dispatchers.IO) {
        dao.getAll().map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun insert(recipe: RecipeDomain) = withContext(Dispatchers.IO) {
        val entity = recipe.toEntity()
        dao.insert(entity)
    }

    override suspend fun insert(prepareMode: PrepareModeDomain) = withContext(Dispatchers.IO) {
        val entity = prepareMode.toEntity()
        dao.insert(entity)
    }

    override suspend fun inset(ingredient: IngredientDomain) = withContext(Dispatchers.IO) {
        val entity = ingredient.toEntity()
        dao.insert(entity)
    }

    override suspend fun getRecipeWithIngredientsAndPrepareMode(idRecipe: Int): Flow<FullRecipeDomain> =
        withContext(Dispatchers.IO) {
            dao.getRecipeWithIngredientsAndPrepareMode(idRecipe).map {
                it.toDomain()
            }
        }

    override suspend fun update(ingredient: IngredientDomain) = withContext(Dispatchers.IO) {
        dao.updateIngredient(ingredient.toEntity())
    }

    override suspend fun update(prepareMode: PrepareModeDomain) = withContext(Dispatchers.IO) {
        dao.updatePrepareMode(prepareMode.toEntity())
    }

    override suspend fun deleteIngredient(id: Int) = withContext(Dispatchers.IO) {
        dao.deleteIngredient(id)
    }

    override suspend fun deletePrepareMode(id: Int) = withContext(Dispatchers.IO) {
        dao.deletePreparedMode(id)
    }
}
