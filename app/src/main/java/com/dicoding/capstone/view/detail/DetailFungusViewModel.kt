package com.dicoding.capstone.view.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.capstone.remote.api.ApiConfig
import com.dicoding.capstone.remote.response.Data
import com.dicoding.capstone.remote.response.FungusDetailResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailFungusViewModel : ViewModel() {

    private val _detailFungus = MutableLiveData<Data?>()
    val detailuser: MutableLiveData<Data?> = _detailFungus

    private val _loading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _loading

    fun getUserDetail(id: String) {
        _loading.value = true
        val apiService = ApiConfig.getApiService().getDetailFungus(id)
        apiService.enqueue(object : Callback<FungusDetailResponse> {
            override fun onResponse(
                call: Call<FungusDetailResponse>,
                response: Response<FungusDetailResponse>
            ) {
                _loading.value = false
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    _detailFungus.value = responseBody?.data
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<FungusDetailResponse>, t: Throwable) {
                _loading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object {
        private const val TAG = "DetailViewModel"
    }
}