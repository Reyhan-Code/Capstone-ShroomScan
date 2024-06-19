package com.dicoding.capstone.view.detail

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dicoding.capstone.databinding.ActivityDetailFungusBinding
import com.dicoding.capstone.remote.response.Data


class DetailFungusActivity : AppCompatActivity() {

    private val binding: ActivityDetailFungusBinding by lazy {
        ActivityDetailFungusBinding.inflate(layoutInflater)
    }

    private val detailViewModel by viewModels<DetailFungusViewModel>()
    private var detailResponse = Data()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(binding.root)

        val usernameArgs = intent.extras?.getString(ID)
        if (usernameArgs == null) {
            finish()
            return
        }

        binding.btnLeft.setOnClickListener {
            Intent(this, DetailFungusActivity::class.java).also {
                finish()
            }
        }

        with(detailViewModel) {
            getUserDetail(usernameArgs)
            isLoading.observe(this@DetailFungusActivity) { showLoading(it) }
            detailuser.observe(this@DetailFungusActivity) { data ->
                if (data != null) {
                    setFungus(data)
                    detailResponse = data
                } else {
                    Log.e("DetailFungusActivity", "No data received")
                }
            }
        }
    }

    private fun setFungus(data: Data) {
        with(binding) {
            nameDetail.text = data.nama
            detailToxicFungus.text = data.jenis
            detailIntroduction.text = data.deskripsi
            detailPlanting.text = data.mediaTanam
            Glide.with(root.context).load(data.gambar1).into(tvDetailImage)
            Glide.with(root.context).load(data.gambar2).into(detailImg2)
            Glide.with(root.context).load(data.gambar3).into(gambar3)
        }
    }

    private fun showLoading(isLoading: Boolean) {
        binding.progresBar.visibility = if (isLoading) View.VISIBLE else View.GONE
    }


    companion object {

        const val ID = "id"

    }
}