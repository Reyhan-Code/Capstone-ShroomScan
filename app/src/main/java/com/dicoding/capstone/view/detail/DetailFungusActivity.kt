package com.dicoding.capstone.view.detail

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.annotation.StringRes
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.dicoding.capstone.R
import com.dicoding.capstone.databinding.ActivityDetailFungusBinding
import com.dicoding.capstone.remote.response.DataItem

class DetailFungusActivity : AppCompatActivity() {

    private val binding: ActivityDetailFungusBinding by lazy {
        ActivityDetailFungusBinding.inflate(layoutInflater)
    }

    private val detailViewModel by viewModels<DetailFungusViewModel>()
    private var detailResponse = DataItem()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        supportActionBar?.hide()

        val usernameArgs = intent.extras?.getString(ID)

        with(detailViewModel) {
            getUserDetail(usernameArgs!!)
            isLoading.observe(this@DetailFungusActivity) { showLoading(it) }
            detailuser.observe(this@DetailFungusActivity) { data ->
                setFungus(data)
                detailResponse = data
            }
        }
    }

    private fun setFungus(data: DataItem) {

        with(binding) {
            detailNameFungus.text = data.nama
            detailToxicFungus.text = data.jenis
            detailIntroduction.text = data.deskripsi
            detailPlanting.text = data.mediaTanam
            Glide.with(root.context).load(data.gambar1).into(tvDetailImage)
            Glide.with(root.context).load(data.gambar2).into(detailImg2)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progresBar.visibility = View.VISIBLE
        } else {
            binding.progresBar.visibility = View.GONE
        }
    }


    companion object {

        const val ID = "id"

    }
}