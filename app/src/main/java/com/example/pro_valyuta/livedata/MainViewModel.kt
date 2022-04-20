package com.example.pro_valyuta.livedata

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pro_valyuta.retrofit.Valyuta

class MainViewModel : ViewModel() {

    private var liveData = MutableLiveData<Valyuta>()
    private val TAG = "MainViewModel"

    fun getCurrency(): MutableLiveData<Valyuta> {


        return liveData
    }
}