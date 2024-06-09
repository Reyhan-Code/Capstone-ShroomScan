package com.dicoding.capstone.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.dicoding.capstone.remote.database.FungusDao
import com.dicoding.capstone.remote.database.FungusEntity

class FungusRepository private constructor(
    private val fungusDao: FungusDao
) {

    fun getRecipe(): LiveData<List<FungusEntity>> {
        return fungusDao.getRecipe()
    }

    fun getFavoriteRecipe(): LiveData<List<FungusEntity>> {
        return fungusDao.getFavoriteRecipe()
    }

    fun searchRecipe(query: String): LiveData<List<FungusEntity>> {
        return fungusDao.searchRecipe(query)
    }

    suspend fun setFavoriteRecipe(recipe: FungusEntity, newState: Boolean) {
       recipe.isFavorite = newState
        fungusDao.update(recipe)
    }

    companion object {
        @Volatile
        private var instance: FungusRepository? = null
        fun getInstance(
            fungusDao: FungusDao
        ): FungusRepository =
            instance ?: synchronized(this) {
                instance ?: FungusRepository(fungusDao)
            }.also { instance = it }
    }
}