package com.example.pro_valyuta.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pro_valyuta.GlavniFragment
import com.example.pro_valyuta.retrofit.Valyuta

class ViewPagerAdapter(
    var list: List<Valyuta>?,
    var listRoom: List<Valyuta>, fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
       return list!!.size
    }

    override fun createFragment(position: Int): Fragment {
        return GlavniFragment.newInstance(position, list!![position].code)
    }
}