package com.dicoding.capstone.adapter

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.dicoding.capstone.databinding.ItemListBinding
import com.dicoding.capstone.remote.response.DataItem
import com.dicoding.capstone.remote.response.ItemsItem
import com.dicoding.capstone.view.detail.DetailFungusActivity

class ListFungusAdapter : ListAdapter<DataItem, ListFungusAdapter.MyViewHolder>(DIFF_CALLBACK) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val user = getItem(position)
        if (user != null) {
            holder.bind(user)
        }
    }



    class MyViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(user: DataItem) {
            with(binding) {
                val arguments = Bundle()
                arguments.putString(ID_KEY, user.id.toString())
                Glide.with(itemView)
                    .load(user.gambar1)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(tvImage)
                tvName.text = user.nama
                tvDescription.text = user.deskripsi
                root.setOnClickListener {
                    val intent =
                        Intent(it.context, DetailFungusActivity::class.java).putExtras(arguments)
                    it.context.startActivity(intent)
                }
            }

        }
    }

    companion object {
        const val ID_KEY = "id"

        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<DataItem>() {
            override fun areItemsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: DataItem, newItem: DataItem): Boolean {
                return oldItem == newItem
            }
        }
    }

}