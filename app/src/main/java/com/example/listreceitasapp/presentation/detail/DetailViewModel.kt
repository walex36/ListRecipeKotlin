package com.example.listreceitasapp.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.listreceitasapp.data.db
import com.example.listreceitasapp.data.repository.RecipeRepositoryImpl
import com.example.listreceitasapp.domain.model.IngredientDomain
import com.example.listreceitasapp.domain.model.PrepareModeDomain
import com.example.listreceitasapp.domain.usecase.DeleteIngredientUseCase
import com.example.listreceitasapp.domain.usecase.DeletePrepareModeUseCase
import com.example.listreceitasapp.domain.usecase.GetRecipeWithIngredientAndPrepareModeUseCase
import com.example.listreceitasapp.domain.usecase.InsertIngredientUseCase
import com.example.listreceitasapp.domain.usecase.InsertPrepareModeUseCase
import com.example.listreceitasapp.domain.usecase.UpdateIngredientUseCase
import com.example.listreceitasapp.domain.usecase.UpdatePrepareModeUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class DetailViewModel(
    private val idRecipe: Int,
    private val getRecipeWithIngredientAndPrepareModeUseCase: GetRecipeWithIngredientAndPrepareModeUseCase,
    private val insertIngredientsUseCase: InsertIngredientUseCase,
    private val insertPrepareModeUseCase: InsertPrepareModeUseCase,
    private val updateIngredientUseCase: UpdateIngredientUseCase,
    private val updatePrepareModeUseCase: UpdatePrepareModeUseCase,
    private val deleteIngredientUseCase: DeleteIngredientUseCase,
    private val deletePrepareModeUseCase: DeletePrepareModeUseCase,
) : ViewModel() {
    private val _state = MutableSharedFlow<ItemListState>()
    val state: SharedFlow<ItemListState> = _state

    init {
        getRecipeWithIngredientsAndPrepareMode()
    }
    private fun getRecipeWithIngredientsAndPrepareMode() = viewModelScope.launch {
        getRecipeWithIngredientAndPrepareModeUseCase(idRecipe)
            .flowOn(Dispatchers.Main)
            .onStart {
                _state.emit(ItemListState.Loading)
            }.catch {
                _state.emit(ItemListState.Error("Erro ao buscar ingredientes e modos de preparo"))
            }.collect {
                _state.emit(
                    ItemListState.Success(
                        ingredients = it.ingredient,
                        prepareMode = it.prepareMode
                    )
                )
            }
    }

    fun insertIngredientsOrPrepareMode(
        typeInsert: String,
        name: String,
        idRecipe: Int,
    ) = viewModelScope.launch {
        if (typeInsert == "INGREDIENT") {
            insertIngredientsUseCase(
                IngredientDomain(
                    name = name,
                    idRecipe = idRecipe
                )
            )
        } else {
            insertPrepareModeUseCase(
                PrepareModeDomain(
                    name = name,
                    idRecipe = idRecipe
                )
            )
        }
    }

    fun updateIngredientsOrPrepareMode(
        typeUpdate: String,
        id: Int,
        name: String,
        idRecipe: Int,
    ) = viewModelScope.launch {
        if (typeUpdate == "INGREDIENT") {
            updateIngredientUseCase(
                IngredientDomain(
                    id = id,
                    name = name,
                    idRecipe = idRecipe
                )
            )
        } else {
            updatePrepareModeUseCase(
                PrepareModeDomain(
                    id = id,
                    name = name,
                    idRecipe = idRecipe
                )
            )
        }
    }

    fun removeIngredientsOrPrepareMode(
        typeDelete: String,
        id: Int,
    ) = viewModelScope.launch {
        if (typeDelete == "INGREDIENT") {
            deleteIngredientUseCase(id)
        } else {
            deletePrepareModeUseCase(id)
        }
    }

    class Factory(var idRecipe: Int) : ViewModelProvider.Factory {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(
            modelClass: Class<T>,
            extras: CreationExtras,
        ): T {
            val application = checkNotNull(extras[APPLICATION_KEY])
            val repository = RecipeRepositoryImpl(application.db.recipeDao())
            return DetailViewModel(
                idRecipe = idRecipe,
                getRecipeWithIngredientAndPrepareModeUseCase = GetRecipeWithIngredientAndPrepareModeUseCase(
                    repository
                ),
                insertIngredientsUseCase = InsertIngredientUseCase(repository),
                insertPrepareModeUseCase = InsertPrepareModeUseCase(repository),
                updateIngredientUseCase = UpdateIngredientUseCase(repository),
                updatePrepareModeUseCase = UpdatePrepareModeUseCase(repository),
                deleteIngredientUseCase = DeleteIngredientUseCase(repository),
                deletePrepareModeUseCase = DeletePrepareModeUseCase(repository),
            ) as T
        }
    }

}