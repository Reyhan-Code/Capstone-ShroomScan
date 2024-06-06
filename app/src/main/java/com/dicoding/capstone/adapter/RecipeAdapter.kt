package com.dicoding.capstone.adapter

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dicoding.capstone.adapter.ListFungusAdapter.Companion.DIFF_CALLBACK
import com.dicoding.capstone.databinding.ItemListBinding
import com.dicoding.capstone.databinding.RecipeListBinding
import com.dicoding.capstone.remote.database.FungusEntity
import com.dicoding.capstone.remote.response.DataItem
import com.dicoding.capstone.view.detail.DetailFungusActivity
import com.dicoding.capstone.view.recipe.detail.DetailRecipeActivity

class RecipeAdapter(private val onFavoriteClick: (FungusEntity) -> Unit) :
    ListAdapter<FungusEntity, RecipeAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = RecipeListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val recipe = getItem(position)
        holder.bind(recipe)
    }

    class MyViewHolder(val binding: RecipeListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(recipe: FungusEntity) {
            binding.tvRecipeName.text = recipe.name_recipe
            binding.tvRecipeDescription.text = recipe.ingredients
            Glide.with(itemView.context)
                .load(recipe.photoUrl)
                .into(binding.imgRecipe)

            itemView.setOnClickListener {
                val intentDetailFood = Intent(itemView.context, DetailRecipeActivity::class.java)
                intentDetailFood.putExtra("Recipe", recipe)
                itemView.context.startActivity(intentDetailFood)
            }
        }
    }

    companion object {
        val DIFF_CALLBACK: DiffUtil.ItemCallback<FungusEntity> =
            object : DiffUtil.ItemCallback<FungusEntity>() {
                override fun areItemsTheSame(
                    oldItem: FungusEntity,
                    newItem: FungusEntity
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                @SuppressLint("DiffUtilEquals")
                override fun areContentsTheSame(
                    oldItem: FungusEntity,
                    newItem: FungusEntity
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }
}