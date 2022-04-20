package com.example.pro_valyuta.Room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.pro_valyuta.retrofit.Valyuta


@Dao
interface ValyutaDao {
    @Insert
    fun insertCurrency(valyuta: Valyuta)

    @Query("select * from valyuta")
    fun getAllCurrency(): List<Valyuta>
}