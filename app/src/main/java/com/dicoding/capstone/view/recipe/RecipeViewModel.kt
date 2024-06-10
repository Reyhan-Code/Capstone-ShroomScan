package com.dicoding.capstone.view.recipe

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.dicoding.capstone.remote.database.recipe.RecipeEntity
import com.dicoding.capstone.repository.FungusRepository
import kotlinx.coroutines.launch
import com.dicoding.capstone.data.Result

class RecipeViewModel(private val repository: FungusRepository) : ViewModel() {


    fun saveRecipe(recipe: RecipeEntity) {
        viewModelScope.launch {
            repository.setFavoriteRecipe(recipe, true)
        }
    }

    fun deleteCulinary(recipe: RecipeEntity) {
        viewModelScope.launch {
            repository.setFavoriteRecipe(recipe, false)
        }
    }

    fun searchRecipe(query: String): LiveData<Result<List<RecipeEntity>>> = liveData {
        emit(Result.Loading)
        try {
            val searchResult = repository.searchRecipe(query)
            emitSource(searchResult.map { Result.Success(it) })
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
    }

}