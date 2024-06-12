package com.dicoding.capstone.view.main

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.capstone.R
import com.dicoding.capstone.adapter.ListFungusAdapter
import com.dicoding.capstone.databinding.ActivityMainBinding
import com.dicoding.capstone.factory.ViewModelFactory
import com.dicoding.capstone.remote.response.DataItem
import com.dicoding.capstone.view.favorit.FavoriteActivity
import com.dicoding.capstone.view.recipe.RecipeActivity
import com.dicoding.capstone.view.scan.ScanActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val mainViewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val layoutManager = LinearLayoutManager(this)
        binding.rvList.apply {
            setHasFixedSize(true)
            this.layoutManager = layoutManager
        }

        val itemDecoration = DividerItemDecoration(this, layoutManager.orientation)
        binding.rvList.addItemDecoration(itemDecoration)

        with(mainViewModel) {
            userData.observe(this@MainActivity) {
                if (it != null) {
                    setDataUser(it)
                }
            }
            isLoading.observe(this@MainActivity) { showLoading(it) }
        }

        with(binding) {
            searchView.setupWithSearchBar(searchBar)
            searchView.editText.setOnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    val query = searchView.text.toString()
                    searchBar.setText(query)
                    Log.d("query", query)
                    searchView.hide()
                    mainViewModel.findFungus(searchView.text.toString())
                    true
                } else {
                    false
                }
            }

            binding.searchBar.setOnMenuItemClickListener { menuItem ->
                when (menuItem.itemId) {
                    R.id.favorite -> {
                        val intent = Intent(this@MainActivity, FavoriteActivity::class.java)
                        Log.d("scan", "Pindah ke Favorite Activity")
                        startActivity(intent)
                        true
                    }

                    else -> false
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

    private fun setDataUser(users: List<DataItem>) {
        val adapter = ListFungusAdapter()
        adapter.submitList(users)
        binding.rvList.adapter = adapter
    }


    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }
}