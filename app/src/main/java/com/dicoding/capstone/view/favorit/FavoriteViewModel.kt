package com.dicoding.capstone.view.favorit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.capstone.remote.database.FungusEntity
import com.dicoding.capstone.repository.FungusRepository
import kotlinx.coroutines.launch

class FavoriteViewModel(private val repository: FungusRepository) : ViewModel() {

    fun getFavoriteRecipe() = repository.getFavoriteRecipe()

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