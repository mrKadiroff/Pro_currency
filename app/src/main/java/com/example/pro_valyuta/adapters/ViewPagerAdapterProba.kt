package com.example.pro_valyuta.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pro_valyuta.GlavniFragment
import com.example.pro_valyuta.livedata.RecyclerFragment
import com.example.pro_valyuta.retrofit.Valyuta

class ViewPagerAdapterProba(
    var listRoom: List<Valyuta>, fragmentManager: FragmentManager, lifecycle: Lifecycle) :
    FragmentStateAdapter(fragmentManager, lifecycle) {

    override fun getItemCount(): Int {
       return listRoom.size
    }

    override fun createFragment(position: Int): Fragment {
        return RecyclerFragment.newInstance(listRoom!![position].code, listRoom!![position].code)
    }
}