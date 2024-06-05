package com.dicoding.capstone.view.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.capstone.remote.database.FungusEntity
import com.dicoding.capstone.repository.FungusRepository
import kotlinx.coroutines.launch

class RecipeViewModel(private val repository: FungusRepository) : ViewModel() {


    fun saveRecipe(recipe: FungusEntity) {
        viewModelScope.launch {
            repository.setFavoriteCulinary(recipe, true)
        }
    }

    fun deleteCulinary(recipe: FungusEntity) {
        viewModelScope.launch {
            repository.setFavoriteCulinary(recipe, false)
        }
    }

}