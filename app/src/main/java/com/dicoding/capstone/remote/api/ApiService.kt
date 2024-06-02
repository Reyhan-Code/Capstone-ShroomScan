package com.dicoding.capstone.remote.api

import com.dicoding.capstone.remote.response.DetailResponse
import com.dicoding.capstone.remote.response.FungusResponse
import com.dicoding.capstone.remote.response.GithubResponse
import com.dicoding.capstone.remote.response.ItemsItem
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("mushrooms")
    fun getUsers(
    ): Call<FungusResponse>


    @GET("users/{username}")
    fun getDetailUser(@Path("username") username: String): Call<DetailResponse>

    @GET("users/{username}/followers")
    fun getFollowers(@Path("username") username: String): Call<List<ItemsItem>>

    @GET("users/{username}/following")
    fun getFollowing(@Path("username") username: String): Call<List<ItemsItem>>
}