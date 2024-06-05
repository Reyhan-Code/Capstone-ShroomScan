package com.dicoding.capstone.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.capstone.adapter.ListFungusAdapter.Companion.DIFF_CALLBACK
import com.dicoding.capstone.databinding.RecipeListBinding
import com.dicoding.capstone.remote.database.FungusEntity
import com.dicoding.capstone.view.recipe.detail.DetailRecipeActivity

class RecipeAdapter (private val onFavoriteClick: (FungusEntity) -> Unit) :
    ListAdapter<FungusEntity, RecipeAdapter.MainViewHolder>(DIFF_CALLBACK){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val binding = RecipeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val recipe = getItem(position)
        holder.bind(recipe)
    }


    class MainViewHolder( val binding: RecipeListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(fungus: FungusEntity) {
            binding.tvRecipeName.text = fungus.name_recipe
            binding.tvRecipeDescription.text = fungus.ingredients
            Glide.with(itemView.context)
                .load(fungus.photoUrl)
                .into(binding.imgRecipe)

            itemView.setOnClickListener {
                val intentDetailFood = Intent(itemView.context, DetailRecipeActivity::class.java)
                intentDetailFood.putExtra("Recipe", fungus)
                itemView.context.startActivity(intentDetailFood)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<FungusEntity> =
            object : DiffUtil.ItemCallback<FungusEntity>() {
                override fun areItemsTheSame(oldItem: FungusEntity, newItem: FungusEntity): Boolean {
                    return oldItem.id == newItem.id
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(oldItem: FungusEntity, newItem: FungusEntity): Boolean {
                    return oldItem == newItem
                }
            }
    }
}