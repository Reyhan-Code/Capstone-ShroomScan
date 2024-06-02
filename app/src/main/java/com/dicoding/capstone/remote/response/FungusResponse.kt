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

	@field:SerializedName("planting_media")
	val plantingMedia: String? = null,

	@field:SerializedName("jenis")
	val jenis: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("gambar")
	val gambar: String? = null,

	@field:SerializedName("introduction")
	val introduction: String? = null
)
