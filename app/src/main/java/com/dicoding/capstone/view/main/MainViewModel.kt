package com.dicoding.capstone.view.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.dicoding.capstone.R
import com.dicoding.capstone.data.Result
import com.dicoding.capstone.remote.api.ApiConfig
import com.dicoding.capstone.remote.database.FungusDb
import com.dicoding.capstone.remote.database.fungus.FungusEntity
import com.dicoding.capstone.remote.database.recipe.RecipeEntity
import com.dicoding.capstone.remote.response.DataItem
import com.dicoding.capstone.remote.response.FungusResponse
import com.dicoding.capstone.remote.response.ResepResponse
import com.dicoding.capstone.repository.FungusRepository
import com.google.gson.Gson
import com.google.gson.JsonParser
import com.google.gson.reflect.TypeToken
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.InputStreamReader

class MainViewModel(repository: FungusRepository) : ViewModel() {


    private val _userData = MutableLiveData<List<DataItem>?>()
    val userData: LiveData<List<DataItem>?> = _userData

    private val _loading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _loading

    init {
        findFungus()
    }

    fun findFungus(query: String = "Jamur ") {
        _loading.value = true
        val apiService = ApiConfig.getApiService().getFungus(query)
        apiService.enqueue(object : Callback<FungusResponse> {
            override fun onResponse(
                call: Call<FungusResponse>,
                response: Response<FungusResponse>
            ) {
                _loading.value = false
                if (response.isSuccessful) {
                    _userData.value = response.body()?.data
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<FungusResponse>, t: Throwable) {
                _loading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }



    companion object {
        private const val TAG = "MainViewModel"
    }

}

