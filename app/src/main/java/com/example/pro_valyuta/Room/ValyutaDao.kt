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



    @Query("select * from valyuta where code=:codeIt")
    fun getCurrencyByCode(codeIt: String): List<Valyuta>



    @Query("select * from valyuta where date=:date and code=:code")
    fun getCurrencyByDate(date: String,code: String): List<Valyuta>
}