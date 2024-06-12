package com.dicoding.capstone.view.favorit

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
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

        viewModel.getFavoriteRecipe().observe(this) { favoriteRecipe ->
            recipeAdapter.submitList(favoriteRecipe)
            if (favoriteRecipe.isNullOrEmpty()) {
                binding.imgNoFavorites.visibility = View.VISIBLE
                Toast.makeText(
                    this,
                    "Add Favorite Recipe",
                    Toast.LENGTH_SHORT
                ).show()
            } else {
                binding.imgNoFavorites.visibility = View.GONE
            }
        }

        binding.btnLeft.setOnClickListener {
            Intent(this, FavoriteActivity::class.java).also {
                finish()
            }
        }

        binding.rvRecipe.layoutManager = LinearLayoutManager(this)
        binding.rvRecipe.adapter = recipeAdapter

        binding.rvRecipe.apply {
            setHasFixedSize(true)
            adapter = recipeAdapter
        }
    }
}