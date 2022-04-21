package com.example.pro_valyuta.photoRetrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitServicePhoto {

    @GET("local/templates/nbu/images/flags/CHF.png")
    fun getPhotoy(
    ): Call<Photo>


}