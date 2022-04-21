package com.example.pro_valyuta.photoRetrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientPhoto {

    const val BASE_URL = "https://nbu.uz/"

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val apiServicePhoto = getRetrofit().create(RetrofitServicePhoto::class.java)
}