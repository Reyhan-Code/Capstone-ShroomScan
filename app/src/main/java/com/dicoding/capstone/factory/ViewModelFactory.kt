package com.dicoding.capstone.factory

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.capstone.di.Injection
import com.dicoding.capstone.repository.FungusRepository
import com.dicoding.capstone.view.favorit.FavoriteViewModel
import com.dicoding.capstone.view.main.MainViewModel
import com.dicoding.capstone.view.recipe.RecipeActivity
import com.dicoding.capstone.view.recipe.RecipeViewModel
import com.dicoding.capstone.view.recipe.detail.DetailRecipeViewModel


class ViewModelFactory(private val repository: FungusRepository) :
    ViewModelProvider.NewInstanceFactory() {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return when {

            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(repository) as T
            }

            modelClass.isAssignableFrom(RecipeViewModel::class.java) -> {
                RecipeViewModel(repository) as T
            }

            modelClass.isAssignableFrom(DetailRecipeViewModel::class.java) -> {
                DetailRecipeViewModel(repository) as T
            }

            modelClass.isAssignableFrom(FavoriteViewModel::class.java) -> {
                FavoriteViewModel(repository) as T
            }
            else -> throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null

        @JvmStatic
        fun getInstance(context: Context): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(Injection.provideRepository(context))
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }
}