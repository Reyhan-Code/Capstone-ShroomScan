package com.dicoding.capstone.view.recipe.detail

import android.os.Bundle
import android.view.ViewTreeObserver
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dicoding.capstone.R
import com.dicoding.capstone.databinding.ActivityDetailRecipeBinding
import com.dicoding.capstone.databinding.ActivityRecipeBinding


class DetailRecipeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailRecipeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_detail_recipe)

        binding = ActivityDetailRecipeBinding.inflate(layoutInflater)

    }
}