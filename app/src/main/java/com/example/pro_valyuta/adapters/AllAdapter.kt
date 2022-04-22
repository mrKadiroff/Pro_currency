package com.example.pro_valyuta.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.pro_valyuta.databinding.AllCurrencyBinding
import com.example.pro_valyuta.databinding.UserListBinding
import com.example.pro_valyuta.retrofit.Valyuta

class AllAdapter(var list: List<Valyuta>) : RecyclerView.Adapter<AllAdapter.Vh>() {

    inner class Vh(var userListBinding: AllCurrencyBinding) :
        RecyclerView.ViewHolder(userListBinding.root){

        fun onBind(valyuta: Valyuta) {
          userListBinding.olinishi.text =  "${valyuta.nbu_buy_price} UZS"
            userListBinding.sotilishi.text = "${valyuta.cb_price} UZS"
            userListBinding.date.text = valyuta.code
            Glide.with(userListBinding.root.context).load("https://nbu.uz/local/templates/nbu/images/flags/${valyuta.code}.png").into(userListBinding.simvol)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(AllCurrencyBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size


}