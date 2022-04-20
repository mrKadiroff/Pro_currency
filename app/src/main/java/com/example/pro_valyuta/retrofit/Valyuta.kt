package com.example.pro_valyuta.retrofit

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class Valyuta(
    val cb_price: String,
    val code: String,
    val date: String,
    val nbu_buy_price: String,
    val nbu_cell_price: String,
    val title: String,
    @PrimaryKey(autoGenerate = true)
    val id: Int
)