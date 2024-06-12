package com.dicoding.capstone.view.recipe.detail

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dicoding.capstone.R
import com.dicoding.capstone.databinding.ActivityDetailRecipeBinding
import com.dicoding.capstone.factory.ViewModelFactory
import com.dicoding.capstone.remote.database.recipe.RecipeEntity
import com.dicoding.capstone.view.recipe.RecipeActivity


class DetailRecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailRecipeBinding


    private val viewModel by viewModels<DetailRecipeViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val recipe: RecipeEntity? = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("Recipe", RecipeEntity::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra("Recipe")
        }

        Glide.with(this)
            .load(recipe?.photoUrl)
            .transition(DrawableTransitionOptions.withCrossFade())
            .into(binding.tvDetailRecipeImage)

        binding.detailNameRecipe.text = recipe?.name_recipe
        binding.tvTime.text = recipe?.time
        binding.tvPorsi.text = recipe?.portion
        binding.bahanRecipe.text = recipe?.ingredients
        binding.tvDetailRecipe.text = recipe?.howToMake

        binding.btnLeft.setOnClickListener {
            Intent(this, RecipeActivity::class.java).also {
                finish()
            }
        }

        binding.btnFav.setImageResource(if (recipe?.isFavorite == false) R.drawable.heart_fav else R.drawable.heart)
        binding.btnFav.setOnClickListener {
            if (recipe?.isFavorite == true) {
                viewModel.deleteRecipe(recipe)
                binding.btnFav.setImageResource(R.drawable.heart_fav)
            } else {
                viewModel.saveRecipe(recipe as RecipeEntity)
                binding.btnFav.setImageResource(R.drawable.heart)
            }
        }
    }
}