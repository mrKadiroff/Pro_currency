package com.example.pro_valyuta.retrofit

object Common {
    var BASE_URL = "https://nbu.uz/"
    val retrofitService: RetrofitService
        get() = RetrofitClient.getRetrofit(BASE_URL).create(RetrofitService::class.java)
}