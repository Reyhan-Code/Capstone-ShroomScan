package com.dicoding.capstone.view.favorit

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.capstone.R
import com.dicoding.capstone.adapter.RecipeAdapter
import com.dicoding.capstone.databinding.ActivityFavoriteBinding
import com.dicoding.capstone.factory.ViewModelFactory

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding

    private val viewModel by viewModels<FavoriteViewModel> {
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recipeAdapter = RecipeAdapter { recipe ->
            if (recipe.isFavorite) {
                viewModel.deleteRecipe(recipe)
            } else {
                viewModel.saveRecipe(recipe)
            }
        }

        viewModel.getFavoriteRecipe().observe(this) { favoriteCulinary ->
            recipeAdapter.submitList(favoriteCulinary)
        }

        binding.rvRecipe.layoutManager = LinearLayoutManager(this)
        binding.rvRecipe.adapter = recipeAdapter

        binding.rvRecipe.apply {
            setHasFixedSize(true)
            adapter = recipeAdapter
        }
    }
}