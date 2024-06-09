package com.dicoding.capstone.view.recipe.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.capstone.remote.database.FungusEntity
import com.dicoding.capstone.repository.FungusRepository
import kotlinx.coroutines.launch

class DetailRecipeViewModel( private val repository: FungusRepository) : ViewModel() {

    fun saveRecipe(recipe: FungusEntity) {
        viewModelScope.launch {
            repository.setFavoriteRecipe(recipe, true)
        }
    }

    fun deleteRecipe(recipe: FungusEntity) {
        viewModelScope.launch {
            repository.setFavoriteRecipe(recipe, false)
        }
    }

}