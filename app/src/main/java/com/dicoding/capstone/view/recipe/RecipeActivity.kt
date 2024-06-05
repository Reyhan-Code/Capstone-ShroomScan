package com.dicoding.capstone.view.recipe

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.capstone.R
import com.dicoding.capstone.adapter.RecipeAdapter
import com.dicoding.capstone.databinding.ActivityRecipeBinding
import com.dicoding.capstone.factory.ViewModelFactory
import com.dicoding.capstone.remote.database.FungusDb
import com.dicoding.capstone.remote.database.FungusEntity
import com.dicoding.capstone.remote.response.ResepResponse
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.dicoding.capstone.data.Result
import com.google.gson.reflect.TypeToken
import java.io.InputStreamReader

class RecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRecipeBinding

    private val viewModel by viewModels<RecipeViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecipeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        initAdapter()
        initSearchBar()
    }


    private fun initAdapter() {
        val recipeAdapter = RecipeAdapter { recipe ->
            if (recipe.isFavorite) {
                viewModel.deleteCulinary(recipe)
            } else {
                viewModel.saveRecipe(recipe)
            }
        }

        getAllRecipe().observe(this) { result ->
            if (result != null) {
                when (result) {
                    is Result.Loading -> {
                        Log.d("RecipeActivity", "Loading data...")
                    }

                    is Result.Success -> {
                        Log.d("RecipeActivity", "Data loaded successfully")
                        recipeAdapter.submitList(result.data)
                    }

                    is Result.Error -> {
                        Log.e("RecipeActivity", "Error loading recipes: ${result.error}")
                    }
                }
            }
        }

        binding.rvRecipe.apply {
            setHasFixedSize(true)
            this.adapter = recipeAdapter
        }
    }

    private fun initSearchBar() {
        with(binding) {
            searchView.setupWithSearchBar(searchBar)

            searchView
                .editText
                .setOnEditorActionListener { textView, actionId, event ->
                    searchBar.setText(searchView.text)
                    searchView.hide()
                    false
                }
        }
    }

    private fun loadRecipeFromJson(): List<ResepResponse> {
        val inputStream = resources.openRawResource(R.raw.recipe)
        val reader = InputStreamReader(inputStream)
        val jsonElement = JsonParser.parseReader(reader)
        val jsonObject = jsonElement.asJsonObject
        val listRecipeJson = jsonObject.getAsJsonArray("listJamurRecipe")
        val type = object : TypeToken<List<ResepResponse>>() {}.type
        return Gson().fromJson(listRecipeJson, type)
    }

    private fun getAllRecipe(): LiveData<Result<List<FungusEntity>>> = liveData {
        emit(Result.Loading)
        val fungusDao = FungusDb.getInstance(this@RecipeActivity).fungusDao()
        try {
            val culinary = loadRecipeFromJson()
            Log.d("Recipe", "Loaded recipes: $culinary")
            val recipeList = culinary.map {
                val isFavorited = fungusDao.isFavorite(it.id)
                FungusEntity(
                    it.id,
                    it.name_recipe,
                    it.ingredients.toString(),
                    it.howToMake.toString(),
                    it.photoUrl,
                    isFavorited
                )
            }
            fungusDao.deleteAll()
            fungusDao.insert(recipeList)
        } catch (e: Exception) {
            emit(Result.Error(e.message.toString()))
        }
        val localData: LiveData<Result<List<FungusEntity>>> =
            fungusDao.getRecipe().map { Result.Success(it) }
        emitSource(localData)
    }
}