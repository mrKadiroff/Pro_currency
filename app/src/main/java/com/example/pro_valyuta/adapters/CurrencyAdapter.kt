package com.example.pro_valyuta.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.pro_valyuta.databinding.UserListBinding
import com.example.pro_valyuta.retrofit.Valyuta

class CurrencyAdapter(var list: List<Valyuta>) : RecyclerView.Adapter<CurrencyAdapter.Vh>() {

    inner class Vh(var userListBinding: UserListBinding) :
        RecyclerView.ViewHolder(userListBinding.root){

        fun onBind(valyuta: Valyuta) {
          userListBinding.olinishi.text = valyuta.nbu_buy_price
            userListBinding.sotilishii.text = valyuta.cb_price
            userListBinding.datee.text = valyuta.date.subSequence(0, valyuta.date.indexOf(" "))
            userListBinding.timee.text = valyuta.date.subSequence(valyuta.date.indexOf(" "), valyuta.date.length)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(UserListBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position])
    }

    override fun getItemCount(): Int = list.size


}