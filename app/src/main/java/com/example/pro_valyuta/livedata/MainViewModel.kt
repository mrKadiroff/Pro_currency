package com.example.pro_valyuta.livedata

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pro_valyuta.retrofit.RetrofitClient
import com.example.pro_valyuta.retrofit.Valyuta
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private var liveData = MutableLiveData<List<Valyuta>>()
    private val TAG = "MainViewModel"

    fun getCurrency(): MutableLiveData<List<Valyuta>> {

        RetrofitClient.apiService.getListCurrency().enqueue(object:
            Callback<List<Valyuta>> {
            override fun onResponse(call: Call<List<Valyuta>>, response: Response<List<Valyuta>>) {
                if (response.isSuccessful){
                    liveData.value = response.body()


                }
            }

            override fun onFailure(call: Call<List<Valyuta>>, t: Throwable) {

            }

        })


        return liveData
    }
}