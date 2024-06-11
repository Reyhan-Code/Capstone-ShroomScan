package com.dicoding.capstone.view.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.capstone.remote.api.ApiConfig
import com.dicoding.capstone.remote.response.DataItem
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailFungusViewModel : ViewModel() {

    private val _detailFungus = MutableLiveData<DataItem>()
    val detailuser: LiveData<DataItem> = _detailFungus

    private val _loading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _loading

    fun getUserDetail(id: String = "") {
        _loading.value = true
        val apiService = ApiConfig.getApiService().getDetailFungus(id)
        apiService.enqueue(object : Callback<DataItem> {
            override fun onResponse(
                call: Call<DataItem>,
                response: Response<DataItem>
            ) {
                _loading.value = false
                if (response.isSuccessful) {
                    _detailFungus.value = response.body()
                } else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DataItem>, t: Throwable) {
                _loading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object {
        private const val TAG = "DetailViewModel"
    }
}