package com.dicoding.capstone.view.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.capstone.remote.api.ApiConfig
import com.dicoding.capstone.remote.response.GithubResponse
import com.dicoding.capstone.remote.response.ItemsItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _userData = MutableLiveData<List<ItemsItem>?>()
    val userData: LiveData<List<ItemsItem>?> = _userData

    private val _loading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _loading


    init {
        findUsers()
    }

    fun findUsers(query: String = "sidikpermana") {
        _loading.value = true
        val apiService = ApiConfig.getApiService().getUsers(query)
        apiService.enqueue(object : Callback<GithubResponse> {
            override fun onResponse(
                call: Call<GithubResponse>,
                response: Response<GithubResponse>
            ) {
                _loading.value = false
                if (response.isSuccessful) {
                    _userData.value = response.body()?.items
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<GithubResponse>, t: Throwable) {
                _loading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }


    companion object {
        private const val TAG = "MainViewModel"
    }

}