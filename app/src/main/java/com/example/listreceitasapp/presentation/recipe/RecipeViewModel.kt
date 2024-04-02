package com.example.listreceitasapp.presentation.recipe

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.listreceitasapp.data.db
import com.example.listreceitasapp.data.repository.RecipeRepositoryImpl
import com.example.listreceitasapp.domain.model.RecipeDomain
import com.example.listreceitasapp.domain.usecase.GetAllRecipesUseCase
import com.example.listreceitasapp.domain.usecase.InsertRecipeUseCase
import kotlinx.coroutines.launch
import java.lang.Exception

class RecipeViewModel(
    private val getAllRecipesUseCase: GetAllRecipesUseCase,
    private val insertRecipeUseCase: InsertRecipeUseCase,
) : ViewModel() {

    val state: LiveData<RecipeState> = liveData {
        emit(RecipeState.Loading)
        val state = try {
            val recipes = getAllRecipesUseCase()
            if (recipes.isEmpty()) {
                RecipeState.Empty
            } else {
                RecipeState.Success(recipes)
            }
        } catch (e: Exception) {
            Log.e("Error", e.message.toString())
            RecipeState.Error(e.message.toString())
        }
        emit(state)
    }

    fun insert(name: String) = viewModelScope.launch {
        insertRecipeUseCase(RecipeDomain(name = name))
    }

    class Factory : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(
            modelClass: Class<T>,
            extras: CreationExtras
        ): T {
            val application = checkNotNull(extras[APPLICATION_KEY])
            val db = application.db.recipeDao()
            val repository = RecipeRepositoryImpl(db)
            return RecipeViewModel(
                GetAllRecipesUseCase(repository),
                InsertRecipeUseCase(repository)
            ) as T
        }
    }

}