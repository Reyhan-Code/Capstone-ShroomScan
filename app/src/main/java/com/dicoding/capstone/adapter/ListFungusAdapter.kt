package com.dicoding.capstone.adapter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dicoding.capstone.databinding.ItemListBinding
import com.dicoding.capstone.remote.response.ItemsItem
import com.dicoding.capstone.view.detail.DetailFungusActivity

class ListFungusAdapter : ListAdapter<ItemsItem, ListFungusAdapter.MyViewHolder>(DIFF_CALLBACK) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        holder.bind(user)
    }


    class MyViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: ItemsItem) {
            with(binding) {
                val arguments = Bundle()
                arguments.putString(USERNAME_KEY, user.login)
                arguments.putString(BIO_KEY, user.followersUrl)
                Glide.with(itemView)
                    .load(user.avatarUrl)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(tvImage)
                tvName.text = user.login
                tvDescription.text = user.followersUrl
                root.setOnClickListener {
                    val intent =
                        Intent(it.context, DetailFungusActivity::class.java).putExtras(arguments)
                    it.context.startActivity(intent)
                }
            }

        }
    }

    companion object {
        const val USERNAME_KEY = "username"
        const val BIO_KEY = "bio"

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ItemsItem>() {
            override fun areItemsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ItemsItem, newItem: ItemsItem): Boolean {
                return oldItem == newItem
            }
        }
    }

}