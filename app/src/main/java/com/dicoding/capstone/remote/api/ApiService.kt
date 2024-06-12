package com.dicoding.capstone.remote.api

import com.dicoding.capstone.remote.response.FungusDetailResponse
import com.dicoding.capstone.remote.response.FungusResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("mushrooms/name")
    fun getFungus(
        @Query("Jamur") query: String
    ): Call<FungusResponse>

    @GET("mushrooms/{id}")
    fun getDetailFungus(@Path("id") id: String): Call<FungusDetailResponse>

}