package com.dicoding.capstone.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dicoding.capstone.R
import com.dicoding.capstone.databinding.RecipeListBinding
import com.dicoding.capstone.remote.database.recipe.RecipeEntity
import com.dicoding.capstone.view.recipe.detail.DetailRecipeActivity

class RecipeAdapter(private val onFavoriteClick: (RecipeEntity) -> Unit) :
    ListAdapter<RecipeEntity, RecipeAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecipeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val recipe = getItem(position)
        holder.bind(recipe)

        val ivFavorite = holder.binding.recipeFavorit
        if (recipe.isFavorite) {
            ivFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    ivFavorite.context,
                    R.drawable.heart
                )
            )
        } else {
            ivFavorite.setImageDrawable(
                ContextCompat.getDrawable(
                    ivFavorite.context,
                    R.drawable.heart_fav
                )
            )
        }
        ivFavorite.setOnClickListener {
            onFavoriteClick(recipe)
        }
    }

    class MyViewHolder(val binding: RecipeListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: RecipeEntity) {
            binding.nameRecipe.text = recipe.name_recipe
            binding.tvDetailRecipe.text = recipe.ingredients

            Glide.with(itemView.context)
                .load(recipe.photoUrl)
                .into(binding.tvRecipeImg)

            itemView.setOnClickListener {
                val intentDetailRecipe = Intent(itemView.context, DetailRecipeActivity::class.java)
                intentDetailRecipe.putExtra("Recipe", recipe)
                itemView.context.startActivity(intentDetailRecipe)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<RecipeEntity> =
            object : DiffUtil.ItemCallback<RecipeEntity>() {
                override fun areItemsTheSame(
                    oldItem: RecipeEntity,
                    newItem: RecipeEntity
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldItem: RecipeEntity,
                    newItem: RecipeEntity
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}