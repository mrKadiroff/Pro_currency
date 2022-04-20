package com.example.pro_valyuta

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.pro_valyuta.Room.AppDatabase
import com.example.pro_valyuta.adapters.ViewPagerAdapter
import com.example.pro_valyuta.databinding.FragmentHomeBinding
import com.example.pro_valyuta.databinding.TabItemBinding
import com.example.pro_valyuta.retrofit.Common
import com.example.pro_valyuta.retrofit.RetrofitService
import com.example.pro_valyuta.retrofit.Valyuta
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    val tabArray = arrayOf(
        "All",
        "Car",
        "Animals",
        "Girls",
        "Weather",
        "All",
        "Car",
        "Animals"
    )

    lateinit var binding: FragmentHomeBinding
    private val TAG = "HomeFragment"
    lateinit var retrofitService: RetrofitService
    lateinit var appDatabase: AppDatabase
    lateinit var currencylist:ArrayList<Valyuta>
    lateinit var data: ArrayList<Valyuta>
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        appDatabase = AppDatabase.getInstance(binding.root.context)


        glavni()

        indicator()

        retrofitService = Common.retrofitService

        retrofitService.getListCurrency().enqueue(object:
        Callback<List<Valyuta>> {
            override fun onResponse(call: Call<List<Valyuta>>, response: Response<List<Valyuta>>) {
                if (response.isSuccessful){
                   val data = response.body()
                    currencylist = ArrayList()
                    currencylist = appDatabase.valyutaDao().getAllCurrency() as ArrayList<Valyuta>
                    if (currencylist.isNullOrEmpty()){
                        data?.forEach {
                            appDatabase.valyutaDao().insertCurrency(it)
                        }
                    }




                }
            }

            override fun onFailure(call: Call<List<Valyuta>>, t: Throwable) {
                Log.d(TAG, "onFailure: ${t.message}")
            }


        })



       return binding.root

    }

    private fun glavni() {
        currencylist = ArrayList()
        currencylist = appDatabase.valyutaDao().getAllCurrency() as ArrayList<Valyuta>


        val viewPager = binding.viewPager2
        val tabLayoutgl =  binding.tabLayoutgalvni

        val adapter = ViewPagerAdapter(currencylist,childFragmentManager, lifecycle)
        viewPager.adapter = adapter
        TabLayoutMediator(tabLayoutgl, viewPager) { tab, position ->
            val itemBinding = TabItemBinding.inflate(layoutInflater)
            tab.customView = itemBinding.root
            itemBinding.titleTv.text = currencylist[position].code


            if (position == 0) {
                itemBinding.titleTv.setBackgroundResource(R.drawable.tab_item_back_selected)
                itemBinding.titleTv.setTextColor(Color.WHITE)

            } else {
                itemBinding.titleTv.setTextColor(Color.parseColor("#303236"))
                itemBinding.titleTv.setBackgroundResource(R.drawable.tab_item_back_unselected)
            }

            //Some implementation
        }.attach()

        binding.tabLayoutgalvni.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                val itemTabBinding = TabItemBinding.bind(tab?.customView!!)
                itemTabBinding.titleTv.setBackgroundResource(R.drawable.tab_item_back_selected)

                itemTabBinding.titleTv.setTextColor(Color.WHITE)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
                val itemTabBinding = TabItemBinding.bind(tab?.customView!!)
                itemTabBinding.titleTv.setTextColor(Color.parseColor("#303236"))
                itemTabBinding.titleTv.setBackgroundResource(R.drawable.tab_item_back_unselected)
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })



    }

    private fun indicator() {
        currencylist = ArrayList()
        currencylist = appDatabase.valyutaDao().getAllCurrency() as ArrayList<Valyuta>
        val viewPager2 = binding.viewPager2
        val tabLayout = binding.tabLayout
        val adapter = ViewPagerAdapter(currencylist,childFragmentManager, lifecycle)
        viewPager2.adapter = adapter

        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            //Some implementation
        }.attach()
    }

    override fun onResume() {
        super.onResume()
        glavni()
        indicator()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}