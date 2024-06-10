package com.dicoding.capstone.repository

import androidx.lifecycle.LiveData
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.dicoding.capstone.remote.api.ApiService
import com.dicoding.capstone.remote.database.FungusDb
import com.dicoding.capstone.remote.database.fungus.FungusDao
import com.dicoding.capstone.remote.database.fungus.FungusEntity
import com.dicoding.capstone.remote.database.recipe.RecipeDao
import com.dicoding.capstone.remote.database.recipe.RecipeEntity

class FungusRepository private constructor(private val recipeDao: RecipeDao ) {


    fun getRecipe(): LiveData<List<RecipeEntity>> {
        return recipeDao.getRecipe()
    }

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