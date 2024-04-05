package com.example.listreceitasapp.presentation.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.listreceitasapp.data.db
import com.example.listreceitasapp.data.repository.RecipeRepositoryImpl
import com.example.listreceitasapp.domain.model.RecipeDomain
import com.example.listreceitasapp.domain.usecase.GetAllRecipesUseCase
import com.example.listreceitasapp.domain.usecase.InsertRecipeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class RecipeViewModel(
    private val getAllRecipesUseCase: GetAllRecipesUseCase,
    private val insertRecipeUseCase: InsertRecipeUseCase,
) : ViewModel() {

    private val _state = MutableSharedFlow<RecipeState>()
    val state: SharedFlow<RecipeState> = _state

    init {
        getAllRecipes()
    }

    private fun getAllRecipes() = viewModelScope.launch {
        getAllRecipesUseCase()
            .flowOn(Dispatchers.Main)
            .onStart {
                _state.emit(RecipeState.Loading)
            }.catch {
                _state.emit(RecipeState.Error("erro"))
            }.collect { recipes ->
                if (recipes.isEmpty()) {
                    _state.emit(RecipeState.Empty)
                } else {
                    _state.emit(RecipeState.Success(recipes))
                }
            }
    }

    fun insert(name: String) = viewModelScope.launch {
        insertRecipeUseCase(RecipeDomain(name = name, prepareTime = "45 min"))
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