package com.dicoding.capstone.remote.response


data class ResepResponse(
    val id: String,
    val name_recipe: String,
    val ingredients: List<String>,
    val howToMake: List<String>,
    val photoUrl: String,
)