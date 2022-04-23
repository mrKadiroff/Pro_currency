package com.example.pro_valyuta.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.pro_valyuta.R
import com.example.pro_valyuta.databinding.ItemSpinnerBinding
import com.example.pro_valyuta.retrofit.Valyuta
import com.squareup.picasso.Picasso

class SpinnerAdapter (var data: ArrayList<Valyuta>) : BaseAdapter() {

    override fun getCount(): Int = data.size

    override fun getItem(position: Int): Valyuta {
        return data[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = ItemSpinnerBinding.inflate(LayoutInflater.from(parent?.context), parent, false)

        Picasso.get()
            .load("https://nbu.uz/local/templates/nbu/images/flags/${data[position].code}.png")
            .error(R.drawable.flag)
            .into(view.image)

        view.code.text = data[position].code
        return view.root
    }
}