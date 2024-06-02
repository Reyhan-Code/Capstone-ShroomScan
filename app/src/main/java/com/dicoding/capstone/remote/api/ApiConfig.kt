package com.dicoding.capstone.remote.api

import com.dicoding.capstone.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiConfig {
    companion object {
        fun getApiService(): ApiService {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level =
                if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            val client = OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build()

            // Pastikan baseUrl diakhiri dengan tanda garis miring
            val baseUrl = if (BuildConfig.BaseURL.endsWith("/")) {
                BuildConfig.BaseURL
            } else {
                BuildConfig.BaseURL + "/"
            }

            val getRetrofit= Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
            return getRetrofit.create(ApiService::class.java)
        }
    }
}