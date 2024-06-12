package com.dicoding.capstone.repository

import androidx.lifecycle.LiveData
import com.dicoding.capstone.remote.database.recipe.RecipeDao
import com.dicoding.capstone.remote.database.recipe.RecipeEntity

class FungusRepository private constructor(private val recipeDao: RecipeDao) {


    fun getFavoriteRecipe(): LiveData<List<RecipeEntity>> {
        return recipeDao.getFavoriteRecipe()
    }

    fun searchRecipe(query: String): LiveData<List<RecipeEntity>> {
        return recipeDao.searchRecipe(query)
    }

    suspend fun setFavoriteRecipe(recipe: RecipeEntity, newState: Boolean) {
        recipe.isFavorite = newState
        recipeDao.update(recipe)
    }

    companion object {
        @Volatile
        private var instance: FungusRepository? = null
        fun getInstance(
            recipeDao: RecipeDao
        ): FungusRepository =
            instance ?: synchronized(this) {
                instance ?: FungusRepository(recipeDao)
            }.also { instance = it }
    }
}