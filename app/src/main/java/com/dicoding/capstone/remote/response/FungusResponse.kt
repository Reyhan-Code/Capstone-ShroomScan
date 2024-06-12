package com.dicoding.capstone.remote.response

import com.google.gson.annotations.SerializedName

data class FungusResponse(

	@field:SerializedName("data")
	val data: List<DataItem>? = null,

	@field:SerializedName("status")
	val status: String? = null
)


data class DataItem(

	@field:SerializedName("nama")
	val nama: String? = null,

	@field:SerializedName("gambar1")
	val gambar1: String? = null,

	@field:SerializedName("gambar3")
	val gambar3: String? = null,

	@field:SerializedName("gambar2")
	val gambar2: String? = null,

	@field:SerializedName("jenis")
	val jenis: String? = null,


	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("deskripsi")
	val deskripsi: String? = null,

	@field:SerializedName("media_tanam")
	val mediaTanam: String? = null
)
