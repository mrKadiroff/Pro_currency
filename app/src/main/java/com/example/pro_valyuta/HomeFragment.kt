package com.example.pro_valyuta

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pro_valyuta.Room.AppDatabase
import com.example.pro_valyuta.adapters.CurrencyAdapter
import com.example.pro_valyuta.adapters.ViewPagerAdapter
import com.example.pro_valyuta.adapters.ViewPagerAdapterProba
import com.example.pro_valyuta.databinding.FragmentHomeBinding
import com.example.pro_valyuta.databinding.TabItemBinding
import com.example.pro_valyuta.livedata.MainViewModel
import com.example.pro_valyuta.retrofit.RetrofitClient
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
    lateinit var datelist:ArrayList<Valyuta>
    lateinit var mainViewModel: MainViewModel
    lateinit var currencyAdapter: CurrencyAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding = FragmentHomeBinding.inflate(layoutInflater,container,false)
        appDatabase = AppDatabase.getInstance(binding.root.context)
        mainViewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)




        glavni()

        indicator()

        RvPager()


       return binding.root

    }

    private fun RvPager() {

//        mainViewModel.getCurrency().observe(requireActivity(), Observer {
//            Log.d(TAG, "onCreateView: ${it}")



            currencylist = ArrayList()
            currencylist = appDatabase.valyutaDao().getAllCurrency() as ArrayList<Valyuta>
            val viewPager2 = binding.viewPagerproba
            val tabLayout = binding.tabLayout
            val adapter = ViewPagerAdapterProba(currencylist,childFragmentManager, lifecycle)
            viewPager2.adapter = adapter

            TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
                //Some implementation
            }.attach()


//
//        })


    }

    private fun glavni() {



        RetrofitClient.apiService.getListCurrency().enqueue(object:
            Callback<List<Valyuta>> {
            override fun onResponse(call: Call<List<Valyuta>>, response: Response<List<Valyuta>>) {
                if (response.isSuccessful){

                    val data = response.body()

                    currencylist = ArrayList()
                    currencylist = appDatabase.valyutaDao().getAllCurrency() as ArrayList<Valyuta>




                    data!!.forEach {

                        datelist = ArrayList()
                        datelist = appDatabase.valyutaDao().getCurrencyByDate(it.date) as ArrayList<Valyuta>
                        if (currencylist.isNullOrEmpty()){
                            appDatabase.valyutaDao().insertCurrency(it)
                        }else if (datelist.isNullOrEmpty()){
                            appDatabase.valyutaDao().insertCurrency(it)
                        }








                    }



                    val viewPager = binding.viewPager2
                    val tabLayoutgl =  binding.tabLayoutgalvni

                    val adapter = ViewPagerAdapter(data,currencylist,childFragmentManager, lifecycle)
                    viewPager.adapter = adapter
                    TabLayoutMediator(tabLayoutgl, viewPager) { tab, position ->
                        val itemBinding = TabItemBinding.inflate(layoutInflater)
                        tab.customView = itemBinding.root
                        itemBinding.titleTv.text = data!![position].code

                        setRv(data[position].code)


                        if (position == 0) {
                            itemBinding.titleTv.setBackgroundResource(R.drawable.tab_item_back_selected)
                            itemBinding.titleTv.setTextColor(Color.WHITE)

                        } else {
                            itemBinding.titleTv.setTextColor(Color.parseColor("#303236"))
                            itemBinding.titleTv.setBackgroundResource(R.drawable.tab_item_back_unselected)
                        }

                        //Some implementation
                    }.attach()












                }
            }

            override fun onFailure(call: Call<List<Valyuta>>, t: Throwable) {

            }

        })



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

    private fun setRv(code:String) {
        val currencyByCode = appDatabase.valyutaDao().getCurrencyByCode(code)

        currencyAdapter = CurrencyAdapter(currencyByCode)
        binding.rv.adapter = currencyAdapter

    }

    private fun indicator() {


        mainViewModel.getCurrency().observe(requireActivity(), Observer {
            Log.d(TAG, "onCreateView: ${it}")



            currencylist = ArrayList()
            currencylist = appDatabase.valyutaDao().getAllCurrency() as ArrayList<Valyuta>
            val viewPager2 = binding.viewPager2
            val tabLayout = binding.tabLayout
            val adapter = ViewPagerAdapter(it,currencylist,childFragmentManager, lifecycle)
            viewPager2.adapter = adapter

            TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
                //Some implementation
            }.attach()



        })







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