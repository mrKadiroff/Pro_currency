package com.example.pro_valyuta.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("uz/exchange-rates/json/")
    fun getListCurrency(): Call<List<Valyuta>>
}