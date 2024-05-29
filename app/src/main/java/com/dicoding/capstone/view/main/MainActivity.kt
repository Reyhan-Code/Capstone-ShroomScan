package com.dicoding.capstone.view.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dicoding.capstone.databinding.ActivityMainBinding
import com.dicoding.capstone.view.recipe.RecipeActivity
import com.dicoding.capstone.view.scan.ScanActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView.editText.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    val query = searchView.text.toString()
                    searchBar.setText(query)
                    Log.d("query", query)
                    searchView.hide()

//                    mainViewModel.findUsers(searchView.text.toString())
                    true
                } else {
                    false
                }
                }

        }

        binding.btnScan.setOnClickListener { startScanActivity() }
        binding.btnRecipe.setOnClickListener { startRecipeActivity() }

   }

    private fun startScanActivity() {
        val intent = Intent(this, ScanActivity::class.java)
        Log.d("scan", "Pindah ke Scan Activity")
        startActivity(intent)
    }

    private fun startRecipeActivity() {
        val intent = Intent(this, RecipeActivity::class.java)
        Log.d("scan", "Pindah ke Scan RecipeActivity")
        startActivity(intent)
    }

}