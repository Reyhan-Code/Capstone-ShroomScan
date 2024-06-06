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

    fun getFavoriteCulinary(): LiveData<List<FungusEntity>> {
        return fungusDao.getFavoriteRecipe()
    }

    suspend fun setFavoriteCulinary(culinary: FungusEntity, newState: Boolean) {
        culinary.isFavorite = newState
        fungusDao.update(culinary)
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