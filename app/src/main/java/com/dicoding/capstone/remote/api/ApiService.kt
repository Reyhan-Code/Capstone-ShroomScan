package com.dicoding.capstone.remote.api

import com.dicoding.capstone.remote.database.fungus.FungusEntity
import com.dicoding.capstone.remote.response.DataItem
import com.dicoding.capstone.remote.response.DetailResponse
import com.dicoding.capstone.remote.response.FungusResponse
import com.dicoding.capstone.remote.response.GithubResponse
import com.dicoding.capstone.remote.response.ItemsItem
import com.dicoding.capstone.remote.response.ResepResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("mushrooms/name")
    fun getFungus(
        @Query("Jamur") query: String
    ): Call<FungusResponse>

    @GET("mushrooms/{id}")
    fun getDetailFungus(@Path("id") id: String): Call<DataItem>

    @GET("mushrooms/name/{name}")
    fun getFungusSearch(
        @Query("q") query: String
    ): Call<FungusResponse>

    @GET("mushrooms")
    suspend fun getFungusData(
        @Query("page") page: Int,
        @Query("size") size: Int
    ): List<FungusEntity>


}